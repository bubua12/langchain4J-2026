package com.bubua12.langchain4j.fc.service;

import cn.hutool.core.date.DateUtil;
import com.fasterxml.jackson.databind.JsonNode;
import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author bubua12
 * @since 2026/02/11 19:52
 */
@Slf4j
public class InvoiceHandler {

    @Tool("根据用户输入的城市进行天气查询")
    public String handle(@P("城市地址") String city) throws Exception {
        log.info("大模型给定的参数city = {}", city);

        // 这里先写死一个城市
        JsonNode weatherV2 = new WeatherService().getWeatherV2("101010100");

        return "查询天气成功 : " + DateUtil.now() + "\t" + weatherV2;
    }
}
