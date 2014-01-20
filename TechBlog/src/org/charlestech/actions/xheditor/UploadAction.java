package org.charlestech.actions.xheditor;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Upload file in xheditor to server
 * 
 * @author Charles Chen
 * 
 */
public class UploadAction extends ActionSupport {

	/**
	 * Serialised version UID
	 */
	private static final long serialVersionUID = -7086967712363789088L;

	private File filedata; // Xheditor uploaded file
	private String filedataFileName; // File name
	private String filedataContentType; // Image file Content

	/* Image parameters */
	private String imgSavePath = "/xheditor_img/"; // Saved path in server
	private String imgFileExt = "image/bmp,image/x-png,image/gif,image/pjpeg";
	// Image allowed types
	private String imgMaxSize = "1024000"; // Image maximum size
	/* Image parameters */

	/* Attachment parameters */
	private String attachSavePath = "/xheditor_link/"; // Saved path in server
	private String attachFileExt = "text/plain,application/octet-stream,application/x-zip-compressed";
	// Attachment allowed types
	private String attachMaxSize = "5120000"; // Attachment maximum size
	/* Attachment parameters */

	private String err; // Error message
	private String msg; // Message, image path

	/**
	 * Upload image file
	 * 
	 * @return
	 * @throws IOException
	 */
	public String uploadImg() throws IOException {
		/* Get output stream in page */
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		ServletOutputStream out = response.getOutputStream();
		/* Get output stream in page ends */

		/* Check file name, type and size */
		if (filedata == null) {
			err = "File does not exist.";
			out.print("{err:'" + err + "',msg:''}");
			return null;
		}
		if (filedataFileName.indexOf(".") < 0) {
			err = "File name is invalid.";
			out.print("{err:'" + err + "',msg:''}");
			return null;
		}
		if (!isTypeAllowed(filedataContentType, imgFileExt)) {
			err = "Image type must be " + imgFileExt;
			out.print("{err:'" + err + "',msg:''}");
			return null;
		}
		if (filedata.length() > Integer.parseInt(imgMaxSize)) {
			err = "Image maximum size is " + imgMaxSize + "B";
			out.print("{err:'" + err + "',msg:''}");
			return null;
		}
		/* Check file name, type and size ends */

		/* Copy image file to server */
		String saveDir = getImgSavePath();
		// Get sub directory, named with date
		String subDir = new java.text.SimpleDateFormat("yyyyMMdd")
				.format(new java.util.Date());
		File saveDirFile = new File(saveDir + "\\" + subDir);
		// If directory does not exist, create new
		if (!saveDirFile.exists())
			saveDirFile.mkdir();
		// Generate a unique file name
		String newFileName = genUniqName(filedataFileName);
		// Copy image file to server
		FileUtils.copyFile(filedata, new File(saveDir + "\\" + subDir + "\\"
				+ newFileName));
		/* Copy image file to server ends */

		// Get image relative path
		String imgPath = imgSavePath + subDir + "/" + newFileName;
//		// If environment development, add project name before the path
//		String env = ServletActionContext.getServletContext().getInitParameter(
//				"environment");
//		if ("development".equals(env))
//			imgPath = "/TechBlog" + imgPath;

		// Output json data
		out.print("{err:'',msg:'" + imgPath + "'}");
		return null;
	}

	/**
	 * Upload attachment
	 * 
	 * @return
	 * @throws IOException
	 */
	public String uploadAttach() throws IOException {
		/* Get output stream in page */
		ActionContext context = ActionContext.getContext();
		HttpServletResponse response = (HttpServletResponse) context
				.get(ServletActionContext.HTTP_RESPONSE);
		ServletOutputStream out = response.getOutputStream();
		/* Get output stream in page ends */

		/* Check file name, type and size */
		if (filedata == null) {
			err = "File does not exist.";
			out.print("{err:'" + err + "',msg:''}");
			return null;
		}
		if (filedataFileName.indexOf(".") < 0) {
			err = "File name is invalid.";
			out.print("{err:'" + err + "',msg:''}");
			return null;
		}
		if (!isTypeAllowed(filedataContentType, attachFileExt)) {
			err = "Attachment type must be " + attachFileExt;
			out.print("{err:'" + err + "',msg:''}");
			return null;
		}
		if (filedata.length() > Integer.parseInt(attachMaxSize)) {
			err = "Attachment maximum size is " + attachMaxSize + "B";
			out.print("{err:'" + err + "',msg:''}");
			return null;
		}
		/* Check file name, type and size ends */

		/* Copy attachment to server */
		String saveDir = getAttachSavePath();
		// Get sub directory, named with date
		String subDir = new java.text.SimpleDateFormat("yyyyMMdd")
				.format(new java.util.Date());
		File saveDirFile = new File(saveDir + "\\" + subDir);
		// If directory does not exist, create new
		if (!saveDirFile.exists())
			saveDirFile.mkdir();
		// Generate a unique file name
		String newFileName = genUniqName(filedataFileName);
		// Copy attachment to server
		FileUtils.copyFile(filedata, new File(saveDir + "\\" + subDir + "\\"
				+ newFileName));
		/* Copy image file to server ends */

		// Get image relative path
		String attachPath = attachSavePath + subDir + "/" + newFileName;
//		// If environment development, add project name before the path
//		String env = ServletActionContext.getServletContext().getInitParameter(
//				"environment");
//		if ("development".equals(env))
//			attachPath = "/TechBlog" + attachPath;

		// Output json data
		out.print("{err:'',msg:'" + attachPath + "'}");
		return null;
	}

	/**
	 * Generate unique file name
	 * 
	 * @param fileName
	 * @return
	 */
	public String genUniqName(String fileName) {
		if (fileName.indexOf(".") < 0)
			return null;
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
		String uniqStr = UUID.randomUUID().toString();
		return uniqStr + "." + ext;
	}

	/**
	 * Check if type is allowed to upload
	 * 
	 * @param contentType
	 * @return
	 */
	public boolean isTypeAllowed(String contentType, String fileExt) {
		boolean allowed = false;
		String[] types = fileExt.split(",");
		for (String type : types) {
			if (type.equals(contentType)) {
				allowed = true;
				break;
			}
		}
		return allowed;
	}

	public File getFiledata() {
		return filedata;
	}

	public void setFiledata(File filedata) {
		this.filedata = filedata;
	}

	public String getFiledataFileName() {
		return filedataFileName;
	}

	public void setFiledataFileName(String filedataFileName) {
		this.filedataFileName = filedataFileName;
	}

	public String getFiledataContentType() {
		return filedataContentType;
	}

	public void setFiledataContentType(String filedataContentType) {
		this.filedataContentType = filedataContentType;
	}

	public String getImgSavePath() {
		return ServletActionContext.getServletContext()
				.getRealPath(imgSavePath);
	}

	public void setImgSavePath(String imgSavePath) {
		this.imgSavePath = imgSavePath;
	}

	public String getImgFileExt() {
		// Get absolute path on server
		return imgFileExt;
	}

	public void setImgFileExt(String imgFileExt) {
		this.imgFileExt = imgFileExt;
	}

	public String getImgMaxSize() {
		return imgMaxSize;
	}

	public void setImgMaxSize(String imgMaxSize) {
		this.imgMaxSize = imgMaxSize;
	}

	public String getErr() {
		return err;
	}

	public void setErr(String err) {
		this.err = err;
	}

	public String getMsg() {
		return msg;
	}

	public String getAttachSavePath() {
		return ServletActionContext.getServletContext().getRealPath(
				attachSavePath);
	}

	public void setAttachSavePath(String attachSavePath) {
		this.attachSavePath = attachSavePath;
	}

	public String getAttachFileExt() {
		return attachFileExt;
	}

	public void setAttachFileExt(String attachFileExt) {
		this.attachFileExt = attachFileExt;
	}

	public String getAttachMaxSize() {
		return attachMaxSize;
	}

	public void setAttachMaxSize(String attachMaxSize) {
		this.attachMaxSize = attachMaxSize;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
