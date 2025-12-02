
package io.admin.modules.flowable.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.admin.common.dto.AjaxResult;
import io.admin.common.dto.antd.Option;
import io.admin.common.utils.SpringUtils;
import io.admin.common.utils.ann.RemarkTool;
import io.admin.framework.config.argument.RequestBodyKeys;
import io.admin.framework.config.security.HasPermission;
import io.admin.framework.data.query.JpaQuery;
import io.admin.modules.flowable.admin.entity.SysFlowableModel;
import io.admin.modules.flowable.admin.service.SysFlowableModelService;
import io.admin.modules.flowable.core.definition.FormDefinition;
import io.admin.modules.flowable.core.definition.ProcessDefinitionRegistry;
import io.admin.modules.flowable.core.dto.ProcessDefinitionInfo;
import io.admin.modules.system.entity.SysRole;
import io.admin.modules.system.entity.SysUser;
import io.admin.modules.system.service.SysRoleService;
import io.admin.modules.system.service.SysUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 流程模型控制器
 */
@Slf4j
@RestController
@RequestMapping("admin/flowable/model")
@AllArgsConstructor
public class ModelDesignController {

    private SysFlowableModelService service;
    private SysUserService sysUserService;
    private SysRoleService roleService;

    private ProcessDefinitionRegistry registry;

    @HasPermission("flowableModel:design")
    @RequestMapping("page")
    public AjaxResult page(String searchText, Pageable pageable) throws Exception {
        Page<SysFlowableModel> page = service.findAll(searchText, pageable);
        return AjaxResult.ok().data(page);
    }


    @HasPermission("flowableModel:design")
    @GetMapping("delete")
    public AjaxResult delete(@RequestParam String id) {
        service.deleteById(id);
        return AjaxResult.ok();
    }

    @HasPermission("flowableModel:design")
    @PostMapping("save")
    public AjaxResult save(@RequestBody SysFlowableModel param, RequestBodyKeys keys) throws Exception {
        service.saveOrUpdateByRequest(param, keys);
        return AjaxResult.ok();
    }

    @HasPermission("flowableModel:design")
    @PostMapping("saveContent")
    public AjaxResult saveContent(@RequestBody SysFlowableModel param) {
        Assert.hasText(param.getContent(), "内容不能为空");
        SysFlowableModel save = service.saveContent(param);
        return AjaxResult.ok().data(save).msg("服务端：保存xml内容成功");
    }

    @HasPermission("flowableModel:deploy")
    @PostMapping("deploy")
    public AjaxResult deploy(@RequestBody SysFlowableModel param) throws InvocationTargetException, IllegalAccessException, JsonProcessingException {
        Assert.hasText(param.getContent(), "内容不能为空");
        SysFlowableModel sysFlowableModel = service.saveContent(param);

        service.deploy(sysFlowableModel.getCode(), sysFlowableModel.getName(), sysFlowableModel.getContent());

        return AjaxResult.ok().msg("部署成功");
    }


    @GetMapping("detail")
    public AjaxResult detail(String id) {
        SysFlowableModel model = service.findOne(id);
        if (StringUtils.isBlank(model.getContent())) {
            String xml = service.createDefaultModel(model.getCode(), model.getName());
            model.setContent(xml);
        }

        ProcessDefinitionInfo info = registry.getInfo(model.getCode());


        return AjaxResult.ok().data(info);
    }




    @GetMapping("javaDelegateOptions")
    public AjaxResult javaDelegateOptions() {
        Map<String, JavaDelegate> beans = SpringUtils.getBeansOfType(JavaDelegate.class);
        List<Option> options = new ArrayList<>();
        for (Map.Entry<String, JavaDelegate> e : beans.entrySet()) {
            String beanName = e.getKey();
            JavaDelegate value = e.getValue();
            Class<? extends JavaDelegate> cls = value.getClass();
            log.info("{}: {}", beanName, cls);
            String remark = RemarkTool.getRemark(cls);

            String label = remark == null ? beanName : remark;
            String key = "${" + beanName + "}";
            options.add(Option.of(key, label));
        }

        return AjaxResult.ok().data(options);
    }

    @GetMapping("formOptions")
    public AjaxResult formOptions(String code) {
        ProcessDefinitionInfo info = registry.getInfo(code);
        List<FormDefinition> formList = info.getFormList();

        return AjaxResult.ok().data(formList);
    }

    @GetMapping("assigneeOptions")
    public AjaxResult assigneeOptions(String searchText) {
        JpaQuery<SysUser> q = new JpaQuery<>();
        q.searchText(searchText,"name","account","phone");
        List<SysUser> userList = sysUserService.findAll(q,Sort.by("name"));


        List<Option> list = new ArrayList<>();

        list.add(Option.of("${INITIATOR}", "* 发起人"));
        list.add(Option.of("${INITIATOR_DEPT_LEADER}", "* 部门领导"));





        for (SysUser sysUser : userList) {
            list.add(Option.of(sysUser.getId(), sysUser.getName()));
        }


        return AjaxResult.ok().data(list);
    }
    @GetMapping("candidateGroupsOptions")
    public AjaxResult candidateGroupsOptions() {
        List<Option> list = new ArrayList<>();

        List<SysRole> roleList = roleService.findAll(Sort.by("seq","name"));

        for (SysRole sysRole : roleList) {
            list.add(Option.of(sysRole.getId(), sysRole.getName()));
        }




        return AjaxResult.ok().data(list);
    }

    @GetMapping("candidateUsersOptions")
    public AjaxResult candidateUsersOptions(String searchText) {
        List<Option> list = new ArrayList<>();

        JpaQuery<SysUser> q = new JpaQuery<>();
        q.searchText(searchText,"name","account","phone");
        List<SysUser> userList = sysUserService.findAll(q,Sort.by("name"));

        for (SysUser sysUser : userList) {
            list.add(Option.of(sysUser.getId(), sysUser.getName()));
        }

        return AjaxResult.ok().data(list);
    }


    @GetMapping("varList")
    public AjaxResult varOptions(String code) {
        ProcessDefinitionInfo info = registry.getInfo(code);
        return AjaxResult.ok().data(info.getConditionVariableList());
    }

}
