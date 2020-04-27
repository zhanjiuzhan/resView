package org.jpcl.resview.view.resolver.impl;

import org.jpcl.resview.view.model.ImageRes;
import org.jpcl.resview.view.resolver.JcAbstractViewResolver;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

public class JcImageViewResolver extends JcAbstractViewResolver {

    private ImageRes imageRes;

    public JcImageViewResolver(ImageRes imageRes) {
        this.imageRes = imageRes;
    }

    @Override
    protected void makeResponse(HttpServletResponse response) throws Exception {
        if (this.imageRes.getType() == ImageRes.JPG) {
            response.setContentType("image/jpeg");
        } else {
            // TODO
        }
        OutputStream out = response.getOutputStream();
        ImageIO.write(this.imageRes.getImg(), this.imageRes.getType(), out);
    }
}
