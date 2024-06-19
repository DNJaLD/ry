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
import com.ruoyi.domain.PutStorage;
import com.ruoyi.service.IPutStorageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * rukuController
 *
 * @author ruoyi
 * @date 2024-06-19
 */
@RestController
@RequestMapping("/ruoyi-demo/putstorage")
public class PutStorageController extends BaseController
{
    @Autowired
    private IPutStorageService putStorageService;

    /**
     * 查询ruku列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:putstorage:list')")
    @GetMapping("/list")
    public TableDataInfo list(PutStorage putStorage)
    {
        startPage();
        List<PutStorage> list = putStorageService.selectPutStorageList(putStorage);
        return getDataTable(list);
    }

    /**
     * 导出ruku列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:putstorage:export')")
    @Log(title = "ruku", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, PutStorage putStorage)
    {
        List<PutStorage> list = putStorageService.selectPutStorageList(putStorage);
        ExcelUtil<PutStorage> util = new ExcelUtil<PutStorage>(PutStorage.class);
        util.exportExcel(response, list, "ruku数据");
    }

    /**
     * 获取ruku详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:putstorage:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(putStorageService.selectPutStorageById(id));
    }

    /**
     * 新增ruku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:putstorage:add')")
    @Log(title = "ruku", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody PutStorage putStorage)
    {
        System.out.println("输入的入库单信息=="+putStorage);


        return toAjax(putStorageService.insertPutStorage(putStorage));
    }

    /**
     * 修改ruku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:putstorage:edit')")
    @Log(title = "ruku", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody PutStorage putStorage)
    {
        return toAjax(putStorageService.updatePutStorage(putStorage));
    }

    /**
     * 删除ruku
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:putstorage:remove')")
    @Log(title = "ruku", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(putStorageService.deletePutStorageByIds(ids));
    }
}
