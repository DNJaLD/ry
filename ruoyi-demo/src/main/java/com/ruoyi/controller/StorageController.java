package com.ruoyi.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.domain.Storage;
import com.ruoyi.service.IStorageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * cangkuController
 *
 * @author ruoyi
 * @date 2024-06-19
 */
@RestController
@RequestMapping("/ruoyi-demo/storage")
public class StorageController extends BaseController
{
    @Autowired
    private IStorageService storageService;

    /**
     * 查询cangku列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:storage:list')")
    @GetMapping("/list")
    public TableDataInfo list(Storage storage)
    {
        startPage();
        List<Storage> list = storageService.selectStorageList(storage);
        return getDataTable(list);
    }

    /**
     * 导出cangku列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:storage:export')")
    @Log(title = "cangku", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Storage storage)
    {
        List<Storage> list = storageService.selectStorageList(storage);
        ExcelUtil<Storage> util = new ExcelUtil<Storage>(Storage.class);
        util.exportExcel(response, list, "cangku数据");
    }

    /**
     * 获取cangku详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:storage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(storageService.selectStorageById(id));
    }

    /**
     * 新增cangku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:storage:add')")
    @Log(title = "cangku", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Storage storage)
    {
        return toAjax(storageService.insertStorage(storage));
    }

    /**
     * 修改cangku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:storage:edit')")
    @Log(title = "cangku", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Storage storage)
    {
        return toAjax(storageService.updateStorage(storage));
    }

    /**
     * 删除cangku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:storage:remove')")
    @Log(title = "cangku", businessType = BusinessType.DELETE)
	@DeleteMapping("/{storageName}")
    public AjaxResult remove(@PathVariable("storageName") String storageName)
    {
        int status = storageService.deleteStorageWithNoProduct(storageName);

        if (status>0){
            return success("删库成功");
        }else if (status<0){
            return warn("操作失败");
        }else {
            return warn("还有商品存在不允许删库");
        }

    }
}
