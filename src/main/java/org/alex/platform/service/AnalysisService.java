package org.alex.platform.service;

import org.alex.platform.pojo.AnalysisCountVO;

import java.util.ArrayList;
import java.util.HashMap;

public interface AnalysisService {
    Integer userCount();
    Integer suiteCount();
    Integer caseCount();
    Integer assertCount();

    ArrayList<AnalysisCountVO> registerWeek();
    ArrayList<AnalysisCountVO> caseWeek();
    ArrayList<AnalysisCountVO> assertWeek();
    ArrayList<AnalysisCountVO> suiteWeek();

    ArrayList<HashMap<String, Object>> executeLogWeek();
    ArrayList<HashMap<String, Object>> assertLogWeek();
}
