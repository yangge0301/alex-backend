package org.alex.platform.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jayway.jsonpath.JsonPath;
import org.alex.platform.exception.ParseException;
import org.apache.commons.lang.StringEscapeUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;
import org.seimicrawler.xpath.JXDocument;
import org.seimicrawler.xpath.JXNode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ParseUtil {
    /**
     * xpath解析
     *
     * @param xmlText
     * @param xpath
     * @return
     * @throws ParseException
     */
    public static String parseXml(String xmlText, String xpath) throws ParseException {
        // 使用更强大JsoupXpath取代dom4j
        try {
            JXDocument document = JXDocument.create(xmlText);
            List<JXNode> nodes = document.selN(xpath);
            List<String> result = new ArrayList();
            for (JXNode node : nodes) {
                result.add(node.toString());
            }
            return JSON.toJSONString(result);
        } catch (Exception e) {
            throw new ParseException("xml格式错误");
        }

//        List<String> result = new ArrayList<>();
//        try {
//            Document document = new SAXReader().read(new StringReader(xmlText));
//            List<Node> nodes = document.selectNodes(xpath);
//            for (Node node : nodes) {
//                result.add(node.getText());
//            }
//        } catch (DocumentException e) {
//            throw new ParseException("xml格式错误");
//        } catch (Exception e) {
//            throw new ParseException("未找到该元素，请确保xpath无误");
//        }
//        return JSON.toJSONString(result);
    }

    /**
     * 解析http状态码
     *
     * @param entity
     * @return
     */
    public static int parseHttpCode(ResponseEntity entity) {
        return entity.getStatusCodeValue();
    }

    /**
     * 解析请求头
     *
     * @param entity
     * @param headerName
     * @return
     * @throws ParseException
     */
    public static String parseHttpHeader(ResponseEntity entity, String headerName) throws ParseException {
        HttpHeaders headerMap = entity.getHeaders();
        if (!headerMap.containsKey(headerName)) {
            throw new ParseException("响应头中未找到该元素，请确保header无误");
        }
        return JSON.toJSONString(entity.getHeaders().get(headerName));
    }

    /**
     * 解析json
     *
     * @param jsonText
     * @param jsonPath
     * @return
     */
    public static String parseJson(String jsonText, String jsonPath) {
        return JSON.toJSONString(JsonPath.read(jsonText, jsonPath));
    }
}
