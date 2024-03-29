/*
*Programmer: Trần Thế Hùng 
*Description: This files is controller for the feature send top up request
 */
package controller;

import dao.PaymentRequestDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.ProtectionDomain;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import model.PaymentRequest;
import model.User;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;

/**
 *
 * @author Asus
 */
@WebServlet(name = "SendPaymentRequestController", urlPatterns = {"/SendPaymentRequestController"})
public class SendPaymentRequestController extends HttpServlet {

    String location = null;

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        //Users are not allowed to access this servlet through the doGet method
        request.getRequestDispatcher("DisplayMarketItemsController").forward(
                request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        ServletFileUpload upload;
        FileItemIterator iterator;
        FileItemStream item;
        String[] dataArray = new String[2];
        User user = (User) request.getSession().getAttribute("user");
        java.util.Date date = new java.util.Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        PaymentRequest paymentRequest;
        String strDate = formatter.format(date);
        String redirect = "DisplayMarketItemsController";// if redirect attribute is null auto redirect to DisplayMarketItemsController
        String newInvoiceImageName;
        double money;

        try {
            if (user == null) { // if session does not contain any user instance
                redirect = "DisplayMarketItemsController";
            } else {
                upload = new ServletFileUpload();
                iterator = upload.getItemIterator(request);
                item = iterator.next();
                dataArray[0] = Streams.asString(item.openStream());
                item = iterator.next();
            
                money = Double.parseDouble(dataArray[0]);

                paymentRequest = new PaymentRequest(user.getId(), money,strDate, "");
                newInvoiceImageName = PaymentRequestDAO.insertPaymentRequest(paymentRequest);
                moveImages(item,newInvoiceImageName);
                
            }
            request.getRequestDispatcher(redirect).forward(request, response);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void moveImages(FileItemStream item,String newInvoiceImageName) {
        InputStream initialStream;
        Path targetDir;
        Path target;
        try {
            //Move image to new location in project folder called image
            if (location == null) {
                location = getLocation();
            }
            initialStream = item.openStream();
            targetDir = Paths.get(location);//get location of image file in project 
            target = targetDir.resolve(newInvoiceImageName + ".webp");//get location of copied image in the project(targetDir + id of inserted record + .webp)
            Files.copy(initialStream, target,
                    StandardCopyOption.REPLACE_EXISTING);//change name and copy image into target file
            IOUtils.closeQuietly(initialStream);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String getLocation() {
        String path;
        String jarPath = null;
        ProtectionDomain domain;
        try {
            //get location of image file in project
            domain = SendPaymentRequestController.class.getProtectionDomain();
            path = domain.getCodeSource().getLocation().getPath();//get location of jar file in class News Controller
            jarPath = URLDecoder.decode(path, "UTF-8");
        } catch (Exception e) {
            System.out.println(e);
        }
        return jarPath.replace("build/web/WEB-INF/classes/", "").substring(1)
                + "web/UI/image/";//return the location invoice images file
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}