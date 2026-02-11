package com.bubua12.langchain4j.image.controller;

import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesis;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisParam;
import com.alibaba.dashscope.aigc.imagesynthesis.ImageSynthesisResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.JsonUtils;
import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 引入第三方平台 使用万象图片生成
 *
 * @author bubua12
 * @since 2026/2/11 14:15
 */
@RestController
public class WanXImageModelController {
    @Resource
    private WanxImageModel wanxImageModel;


    @GetMapping(value = "/image/create2")
    public String createImageContent() {
        Response<Image> imageResponse = wanxImageModel.generate("一个太阳");
        System.out.println(imageResponse.content().url());

        return imageResponse.content().url().toString();
    }


    @GetMapping(value = "/image/create3")
    public String createImageContentV2() {
        String prompt = "近景镜头，18岁的中国女孩，古代服饰，圆脸，正面看着镜头，" +
                "民族优雅的服装，商业摄影，室外，电影级光照，半身特写，精致的淡妆，锐利的边缘。";
        ImageSynthesisParam param =
                ImageSynthesisParam.builder()
                        .apiKey(System.getenv("BAILIAN_API_KEY"))
                        .model(ImageSynthesis.Models.WANX_V1)
                        .prompt(prompt)
                        .style("<watercolor>")
                        .n(1)
                        .size("1024*1024")
                        .build();

        ImageSynthesis imageSynthesis = new ImageSynthesis();
        ImageSynthesisResult result;

        try {
            System.out.println("---sync call, please wait a moment----");
            result = imageSynthesis.call(param);
        } catch (ApiException | NoApiKeyException e) {
            throw new RuntimeException(e.getMessage());
        }


        System.out.println(JsonUtils.toJson(result));

        return JsonUtils.toJson(result);
    }
}
