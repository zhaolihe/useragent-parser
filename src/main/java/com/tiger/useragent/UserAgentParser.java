package com.tiger.useragent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.generic.GenericData;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class UserAgentParser extends Parser {
  private static final Logger logger = LoggerFactory.getLogger(UserAgentParser.class);
  private static final String CACHE_FILE = "./UserAgentCache.dat";
  private static final String CACHE_VERSION_KEY = "cache.version";
  // once config file changes, increase this number
  private static final long CACHE_VERSION_VALUE = 01;
  private static final Schema CACHE_SCHEMA;
  private static UserAgentParser instance;

  static {
    CACHE_SCHEMA = Schema.createMap(UserAgentInfo.getClassSchema());
    GenericData.setStringType(CACHE_SCHEMA, GenericData.StringType.String);
  }

  private final LoadingCache<String, UserAgentInfo> cache;

  /** construct a new UserAgentParser with custom cache */
  public UserAgentParser(LoadingCache<String, UserAgentInfo> cache) throws IOException {
    this.cache = cache;
    loadCache();
  }

  /** construct a new UserAgentParser */
  public UserAgentParser() throws IOException {
    CacheLoader<String, UserAgentInfo> loader =
        new CacheLoader<String, UserAgentInfo>() {
          @Override
          public UserAgentInfo load(String key) {
            return parse(key);
          }
        };
    cache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES).build(loader);
    loadCache();
  }

  /** get a thread-safe "singleton" UserAgentParser */
  public static UserAgentParser getInstance() throws IOException {
    if (instance == null) {
      synchronized (LOCK) {
        if (instance == null) {
          instance = new UserAgentParser();
        }
      }
    }
    return instance;
  }

  /** construct a new UserAgentParser */
  public static UserAgentParser newInstance() throws IOException {
    return new UserAgentParser();
  }

  /**
   * Get UserAgentInfo from cache, if not found in the cache, it will call the {@link
   * CacheLoader#load} method.
   *
   * @param agentString the user agent string to parse
   * @return the parsed UserAgentInfo
   */
  public UserAgentInfo getUserAgentInfo(String agentString) {
    return cache.getUnchecked(agentString);
  }

  /**
   * Parse the user agent string to UserAgentInfo, without cache
   *
   * @param agentString the user agent string to parse
   * @return the parsed UserAgentInfo
   */
  public UserAgentInfo parseUserAgent(String agentString) {
    return parse(agentString);
  }

  // region cache method
  /** Load default cache file in {@link UserAgentParser#CACHE_FILE} */
  public void loadCache() {
    loadCache(CACHE_FILE);
  }

  /**
   * Load a specified cache file.
   *
   * @param filePath cache file path, local file only.
   */
  public void loadCache(String filePath) {
    DatumReader<Map<String, UserAgentInfo>> datumReader = new SpecificDatumReader<>(CACHE_SCHEMA);
    DataFileReader<Map<String, UserAgentInfo>> reader = null;
    try {
      reader = new DataFileReader<>(new File(filePath), datumReader);
      if (reader.getMetaLong(CACHE_VERSION_KEY) != CACHE_VERSION_VALUE) {
        logger.warn(
            "UserAgentCache version NOT matched, Expected {}, Actual {}.",
            CACHE_VERSION_VALUE,
            reader.getMetaLong(CACHE_VERSION_KEY));
        return;
      }
      while (reader.hasNext()) {
        cache.putAll(reader.next());
      }
    } catch (IOException ignore) {
      logger.warn("Deserialize UserAgentInfo FAILED when opening: {}", filePath);
    } catch (Exception e) {
      logger.warn("Deserialize UserAgentInfo FAILED: ", e);
    } finally {
      try {
        if (null != reader) {
          reader.close();
        }
      } catch (IOException e) {
        logger.warn("close DataFileReader FAILED: ", e);
      }
    }
  }

  /** Save default cache file to {@link UserAgentParser#CACHE_FILE} */
  public void saveCache() {
    saveCache(CACHE_FILE);
  }

  /**
   * Save cache file to a specified path.
   *
   * @param filePath cache file path, local file only.
   */
  public void saveCache(String filePath) {
    DatumWriter<Map<String, UserAgentInfo>> datumWriter = new SpecificDatumWriter<>(CACHE_SCHEMA);
    DataFileWriter<Map<String, UserAgentInfo>> writer = null;
    try {
      writer = new DataFileWriter<>(datumWriter);
      writer.setMeta(CACHE_VERSION_KEY, CACHE_VERSION_VALUE);
      writer.create(CACHE_SCHEMA, new File(filePath));
      writer.append(cache.asMap());
    } catch (IOException e) {
      logger.warn("Serialize UserAgentInfo FAILED: ", e);
    } finally {
      try {
        if (null != writer) {
          writer.close();
        }
      } catch (IOException e) {
        logger.warn("close DataFileWriter FAILED: ", e);
      }
    }
  }
  // endregion
}
