package org.charlestech.beans.apps.compress;

import com.opensymphony.xwork2.ActionContext;
import com.yahoo.platform.yui.compressor.CssCompressor;
import com.yahoo.platform.yui.compressor.JavaScriptCompressor;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.charlestech.utils.StreamUtils;
import org.mozilla.javascript.ErrorReporter;
import org.mozilla.javascript.EvaluatorException;

import java.io.*;

/*
 * @Description Compress Javascript and CSS file using YUI Library
 * @author Charles Chen
 * @date 14-3-17
 * @version 1.0
 */
public class YUICompressor {
    private final String DEFAULT_CHARSET = "utf-8";

    public enum Type {
        JS, CSS
    }

    private String tempInputFile;   // For saving source js/css code
    private String tempOutputFile;  // For saving the js/css compressed result

    /**
     * Compress a existing JS or CSS file to a new file
     *
     * @param inputFilename  Existing file name
     * @param outputFilename New file name
     * @param fileType       File type, ["js", "css"]
     * @param charset        Charset
     * @return 0: success, 1: Invalid file type, 2: Exception, 3: Invalid parameters
     */
    public synchronized int compressFile(String inputFilename, String outputFilename, String fileType, String charset) {
        Reader in = null;
        Writer out = null;
        if (charset == null || StringUtils.isEmpty(charset)) charset = DEFAULT_CHARSET;
        if (inputFilename == null || fileType == null) return 3;
        try {
            File intputFile = new File(inputFilename);
            if (!intputFile.exists()) return 3;

            in = new InputStreamReader(new FileInputStream(inputFilename), charset);
            switch (Type.valueOf(fileType.toUpperCase())) {
                case JS:
                    // Create javascript compressor
                    JavaScriptCompressor jsCompressor = new JavaScriptCompressor(in, new ErrorReporter() {
                        public void warning(String message, String sourceName,
                                            int line, String lineSource, int lineOffset) {
                            if (line < 0) {
                                System.err.println("\n[WARNING] " + message);
                            } else {
                                System.err.println("\n[WARNING] " + line + ':' + lineOffset + ':' + message);
                            }
                        }

                        public void error(String message, String sourceName,
                                          int line, String lineSource, int lineOffset) {
                            if (line < 0) {
                                System.err.println("\n[ERROR] " + message);
                            } else {
                                System.err.println("\n[ERROR] " + line + ':' + lineOffset + ':' + message);
                            }
                        }

                        public EvaluatorException runtimeError(String message, String sourceName,
                                                               int line, String lineSource, int lineOffset) {
                            error(message, sourceName, line, lineSource, lineOffset);
                            return new EvaluatorException(message);
                        }
                    });

                    // Close the input stream first, and then open the output stream,
                    // in case the output file should override the input file.
                    in.close();
                    in = null;

                    if (outputFilename == null) {
                        out = new OutputStreamWriter(System.out, charset);
                    } else {
                        File outputFile = new File(outputFilename);
                        if (!outputFile.exists()) outputFile.createNewFile();
                        out = new OutputStreamWriter(new FileOutputStream(outputFile), charset);
                    }

                    jsCompressor.compress(out, -1, false, false,
                            false, false);
                    break;
                case CSS:
                    // Close the input stream first, and then open the output stream,
                    // in case the output file should override the input file.
                    CssCompressor cssCompressor = new CssCompressor(in);

                    // Close the input stream first, and then open the output stream,
                    // in case the output file should override the input file.
                    in.close();
                    in = null;

                    if (outputFilename == null) {
                        out = new OutputStreamWriter(System.out, charset);
                    } else {
                        out = new OutputStreamWriter(new FileOutputStream(outputFilename), charset);
                    }

                    cssCompressor.compress(out, -1);
                    break;
                default:
                    return 1;
            }
        } catch (Exception e) {
            usage();
            return 2;
        } finally {

            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    /**
     * Compress javascript code
     *
     * @param jsContent
     * @return
     */
    public synchronized String compress(String jsContent, String type) {
        String source = getTempInputFile();
        String dest = getTempOutputFile();
        OutputStreamWriter ow = null;
        String compressed = null;
        try {
            /* Start: Saving source code in temp file */
            File sourceFile = new File(source);
            if (!sourceFile.exists()) {
                sourceFile.getParentFile().mkdirs();
                sourceFile.createNewFile();
            }
            ow = new OutputStreamWriter(new FileOutputStream(sourceFile));
            ow.write(jsContent);
            ow.close();
            /* End: Saving source code in temp file */
            int retcode = compressFile(source, dest, type, null);
            if (retcode == 0) {
                compressed = StreamUtils.inputStreamToString(new FileInputStream(dest), DEFAULT_CHARSET);
            }
            // Delete the temp files
            cleanTemp(new String[]{source, dest});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compressed;
    }

    /**
     * To save space, delete all temp files
     *
     * @param filePaths
     */
    public void cleanTemp(String[] filePaths) {
        File file = null;
        for (String filePath : filePaths) {
            file = new File(filePath);
            if (!file.exists()) continue;
            file.delete();
        }
    }

    private static void usage() {
        System.out.println(
                "\nUsage: java -jar yuicompressor-x.y.z.jar [options] [input file]\n\n"

                        + "Global Options\n"
                        + "  -h, --help                Displays this information\n"
                        + "  --type <js|css>           Specifies the type of the input file\n"
                        + "  --charset <charset>       Read the input file using <charset>\n"
                        + "  --line-break <column>     Insert a line break after the specified column number\n"
                        + "  -v, --verbose             Display informational messages and warnings\n"
                        + "  -o <file>                 Place the output into <file>. Defaults to stdout.\n\n"

                        + "JavaScript Options\n"
                        + "  --nomunge                 Minify only, do not obfuscate\n"
                        + "  --preserve-semi           Preserve all semicolons\n"
                        + "  --disable-optimizations   Disable all micro optimizations\n\n"

                        + "If no input file is specified, it defaults to stdin. In this case, the 'type'\n"
                        + "option is required. Otherwise, the 'type' option is required only if the input\n"
                        + "file extension is neither 'js' nor 'css'.");
    }

    public static void main(String[] args) {
        YUICompressor compressor = new YUICompressor();
        String source = "D:\\Development\\IntelliJ IDEA 13.0.1\\IdeaProjects\\TechBlogSpace\\out\\artifacts\\TechBlog_war_exploded\\temp\\compress_source.txt";
        String dest = "D:\\Development\\IntelliJ IDEA 13.0.1\\IdeaProjects\\TechBlogSpace\\out\\artifacts\\TechBlog_war_exploded\\temp\\compress_dest.txt";
        System.out.println(compressor.compressFile(source, dest, "js", null));
    }

    public String getTempInputFile() {
        return ServletActionContext.getServletContext().getRealPath(tempInputFile);
    }

    public void setTempInputFile(String tempInputFile) {
        this.tempInputFile = tempInputFile;
    }

    public String getTempOutputFile() {
        return ServletActionContext.getServletContext().getRealPath(tempOutputFile);
    }

    public void setTempOutputFile(String tempOutputFile) {
        this.tempOutputFile = tempOutputFile;
    }
}
