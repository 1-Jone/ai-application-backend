package com.gzx.aidada.controller;

import com.gzx.aidada.model.dto.statistic.AppAnswerCountDTO;
import com.gzx.aidada.model.dto.statistic.AppAnswerResultCountDTO;
import com.gzx.aidada.common.BaseResponse;
import com.gzx.aidada.common.ErrorCode;
import com.gzx.aidada.common.ResultUtils;
import com.gzx.aidada.exception.ThrowUtils;
import com.gzx.aidada.mapper.UserAnswerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * App 统计分析接口
 *
 */
@RestController
@RequestMapping("/app/statistic")
@Slf4j
public class AppStatisticController {

    @Resource
    private UserAnswerMapper userAnswerMapper;

    /**
     * 热门应用及回答数统计（top 10）
     *
     * @return
     */
    @GetMapping("/answer_count")
    public BaseResponse<List<AppAnswerCountDTO>> getAppAnswerCount() {
        return ResultUtils.success(userAnswerMapper.doAppAnswerCount());
    }

    /**
     * 某应用回答结果分布统计
     *
     * @param appId
     * @return
     */
    @GetMapping("/answer_result_count")
    public BaseResponse<List<AppAnswerResultCountDTO>> getAppAnswerResultCount(Long appId) {
        ThrowUtils.throwIf(appId == null || appId <= 0, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(userAnswerMapper.doAppAnswerResultCount(appId));
    }
}
