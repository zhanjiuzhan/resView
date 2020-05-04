package org.jpcl.resview.demo.controller;

import org.jpcl.resview.access.utils.ImageVerificationCode;
import org.jpcl.resview.view.JcImageView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.image.BufferedImage;

@Controller
@RequestMapping("/image")
public class ImageController {
    @RequestMapping("/get")
    public JcImageView getImage() throws Exception {
        ImageVerificationCode image = new ImageVerificationCode();
        BufferedImage buf = image.getImage();
        System.out.println(image.getText());
        return new JcImageView(buf);
    }
}
