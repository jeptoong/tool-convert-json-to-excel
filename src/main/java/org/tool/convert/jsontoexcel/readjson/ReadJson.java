package org.tool.convert.jsontoexcel.readjson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.beanutils.ConversionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tool.convert.exception.ConverterUnsupportedException;
import org.tool.convert.exception.JSONFileInvalidException;

public class ReadJson {

  private static final Logger LOG = LoggerFactory.getLogger(ReadJson.class);

  public List<Map<String, String>> readListFromFile(String path) {
    List<Map<String, String>> result = null;

    InputStream fileStream = null;
    try {
      File jsonFile = new File(path);
      fileStream = new FileInputStream(jsonFile);

      JacksonConverter converter = new JacksonConverter();
      if(converter.support(Map.class)) {
        AtomicReference<Throwable> cause = new AtomicReference<>();
        result = (List) converter.read(Map.class, fileStream, "", JacksonConverter.WRAP_CONTEXT_LIST, cause);

        Throwable thr = cause.get();
        if(null != thr) {
          throw new ConversionException(thr);
        }
      } else {
        throw new ConverterUnsupportedException("Converter do not support class " + Map.class.getName());
      }

    } catch (IOException e) {
      throw new JSONFileInvalidException("Error occur when read JSON file", e);
    } finally {
      try {
        fileStream.close();
      } catch (IOException e) {
        LOG.error(e.getMessage(), e);
      }
    }

    return result;
  }
}
