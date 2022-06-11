package com.foelep.system.survey;

import com.foelep.common.core.utils.poi.ExcelUtil;
import com.foelep.common.core.web.controller.BaseController;
import com.foelep.common.core.web.domain.AjaxResult;
import com.foelep.common.core.web.page.TableDataInfo;
import com.foelep.common.log.annotation.Log;
import com.foelep.common.log.enums.BusinessType;
import com.foelep.common.security.annotation.RequiresPermissions;
import com.foelep.system.domain.survey.SurveyQuestion;
import com.foelep.system.service.survey.ISurveyQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 问卷题目Controller
 *
 * @author survey
 * @date 2021-03-21
 */
@RestController
@RequestMapping("/survey/question")
public class SurveyQuestionController extends BaseController {
    @Autowired
    private ISurveyQuestionService surveyQuestionService;

    /**
     * 查询问卷题目列表
     */
    @RequiresPermissions("survey:question:list")
    @GetMapping("/list")
    public TableDataInfo list(SurveyQuestion surveyQuestion) {
        startPage();
        List<SurveyQuestion> list = surveyQuestionService.selectSurveyQuestionList(surveyQuestion);
        return getDataTable(list);
    }

    /**
     * 导出问卷题目列表
     */
    @RequiresPermissions("survey:question:export")
    @Log(title = "问卷题目", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public void export(HttpServletResponse response, SurveyQuestion surveyQuestion) {
        List<SurveyQuestion> list = surveyQuestionService.selectSurveyQuestionList(surveyQuestion);
        ExcelUtil<SurveyQuestion> util = new ExcelUtil<SurveyQuestion>(SurveyQuestion.class);
        util.exportExcel(response, list, "question");
    }

    /**
     * 获取问卷题目详细信息
     */
    @RequiresPermissions("survey:question:query")
    @GetMapping(value = "/{questionId}")
    public AjaxResult getInfo(@PathVariable("questionId") Long questionId) {
        return AjaxResult.success(surveyQuestionService.selectSurveyQuestionById(questionId));
    }

    /**
     * 新增问卷题目
     */
    @RequiresPermissions("survey:question:add")
    @Log(title = "问卷题目", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SurveyQuestion surveyQuestion) {
        return toAjax(surveyQuestionService.insertSurveyQuestion(surveyQuestion));
    }

    /**
     * 修改问卷题目
     */
    @RequiresPermissions("survey:question:edit")
    @Log(title = "问卷题目", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SurveyQuestion surveyQuestion) {
        return toAjax(surveyQuestionService.updateSurveyQuestion(surveyQuestion));
    }

    /**
     * 删除问卷题目
     */
    @RequiresPermissions("survey:question:remove")
    @Log(title = "问卷题目", businessType = BusinessType.DELETE)
    @DeleteMapping("/{questionIds}")
    public AjaxResult remove(@PathVariable Long[] questionIds) {
        return toAjax(surveyQuestionService.deleteSurveyQuestionByIds(questionIds));
    }
}