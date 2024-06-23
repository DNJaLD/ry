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
import com.ruoyi.domain.Inventory;
import com.ruoyi.service.IInventoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * kucunController
 *
 * @author ruoyi
 * @date 2024-06-19
 */
@RestController
@RequestMapping("/ruoyi-demo/inventory")
public class InventoryController extends BaseController
{
    @Autowired
    private IInventoryService inventoryService;

    /**
     * 查询kucun列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:inventory:list')")
    @GetMapping("/list")
    public TableDataInfo list(Inventory inventory)
    {
        startPage();
        List<Inventory> list = inventoryService.selectInventoryList(inventory);
        return getDataTable(list);
    }

    /**
     * 导出kucun列表
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:inventory:export')")
    @Log(title = "kucun", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Inventory inventory)
    {
        List<Inventory> list = inventoryService.selectInventoryList(inventory);
        ExcelUtil<Inventory> util = new ExcelUtil<Inventory>(Inventory.class);
        util.exportExcel(response, list, "kucun数据");
    }

    /**
     * 获取kucun详细信息
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:inventory:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(inventoryService.selectInventoryById(id));
    }

    /**
     * 新增kucun
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:inventory:add')")
    @Log(title = "kucun", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Inventory inventory)
    {
        return toAjax(inventoryService.insertInventory(inventory));
    }

    /**
     * 修改kucun
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:inventory:edit')")
    @Log(title = "kucun", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Inventory inventory)
    {
        return toAjax(inventoryService.updateInventory(inventory));
    }

    /**
     * 删除kucun
     */
    @PreAuthorize("@ss.hasPermi('ruoyi-demo:inventory:remove')")
    @Log(title = "kucun", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {

        int i = inventoryService.deleteInventoryByIds(ids);

        if (i==0){
            return warn("还有该商品未出库请出库所有该商品在删");
        }

        return toAjax(1);
    }
}
