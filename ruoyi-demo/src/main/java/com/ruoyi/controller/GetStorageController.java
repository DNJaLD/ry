package com.ruoyi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.vo.GetStorageInfoVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.domain.GetStorage;
import com.ruoyi.service.IGetStorageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * chukuController
 *
 * @author ruoyi
 * @date 2024-06-20
 */
@RestController
@RequestMapping("/ruoyi-demo/getstorage")
public class GetStorageController extends BaseController
{
    @Autowired
    private IGetStorageService getStorageService;

    /**
     * 查询chuku列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:getstorage:list')")
    @GetMapping("/list")
    public TableDataInfo list(GetStorage getStorage)
    {
        startPage();
        List<GetStorage> list = getStorageService.selectGetStorageList(getStorage);
        return getDataTable(list);
    }

    /**
     * 导出chuku列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:getstorage:export')")
    @Log(title = "chuku", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GetStorage getStorage)
    {
        List<GetStorage> list = getStorageService.selectGetStorageList(getStorage);
        ExcelUtil<GetStorage> util = new ExcelUtil<GetStorage>(GetStorage.class);
        util.exportExcel(response, list, "chuku数据");
    }

    /**
     * 获取chuku详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:getstorage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(getStorageService.selectGetStorageById(id));
    }

    /**
     * 新增chuku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:getstorage:add')")
    @Log(title = "chuku", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GetStorage getStorage)
    {
        return toAjax(getStorageService.insertGetStorage(getStorage));
    }

    /**
     * 修改chuku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:getstorage:edit')")
    @Log(title = "chuku", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GetStorageInfoVo getStorage)
    {
        System.out.println("出库但修改数据==="+getStorage);
        int i = getStorageService.updateGetStorage(getStorage);
        if (i==0){
            return warn("超过该仓库商品数量总数");
        }else if (i==1){
            return toAjax(1);
        }else {
            return error();
        }
//        return toAjax(1);
    }

    /**
     * 删除chuku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:getstorage:remove')")
    @Log(title = "chuku", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(getStorageService.deleteGetStorageByIds(ids));
    }
}
