package com.winter.controller.admin.systemManager;

import com.winter.model.dictionary;
import com.winter.model.dictionary_type;
import com.winter.model.modelUtil.DicWithTypeName;
import com.winter.services.DictionaryService;
import com.winter.services.DictionaryTypeService;
import com.winter.utils.LayUItableResponse;
import com.winter.utils.ResponseCode;
import com.winter.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 数据字典
 */
@Controller
@RequestMapping(value = "/admin")
public class SystemdataController {
    @Autowired
    DictionaryService dictionaryService;

    @Autowired
    DictionaryTypeService dictionaryTypeService;

    @RequestMapping(value = "systemdata", method = RequestMethod.GET)
    public String SystemData(Model model) {
        //返回数据：默认前十条，返回总条数，返回所有的字典类型,返回类型名称

        List<dictionary_type> dicTyprList = dictionaryTypeService.selectAll();

        //得到需要返回的数据

//        int count = dictionaryService.selectCount();

//        model.addAttribute("count", count);
        model.addAttribute("typeList", dicTyprList);

        return "admin/systemmanager/systemdata";
    }

    /**
     * 分页查询
     *
     * @param curr 页码的参数名称
     * @param nums 每页数据量的参数名
     * @return
     */
    @RequestMapping(value = "systemdataget", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> SystemDataGet(@RequestParam("curr") int curr, @RequestParam("nums") int nums) {
        List<dictionary> dicList = dictionaryService.selectBypage(curr, nums);
        int countNum = dictionaryService.selectCount();
        List<DicWithTypeName> resList = getResultDataList(dicList);
        return LayUItableResponse.addData(0, "", countNum, resList);
    }

    /**
     * 数据字典类型添加
     *
     * @param dicTypeName
     * @return
     */
    @RequestMapping(value = "dictypeadd")
    @ResponseBody
    public String addType(@RequestParam("dicTypeName") String dicTypeName) {
        String result = ResponseCode.error;
        dictionary_type model = new dictionary_type();
        if (dicTypeName != "") {
            model.setDictypeid(StringUtil.getUUID());
            model.setDictypename(dicTypeName);

            dictionaryTypeService.insert(model);
            result = ResponseCode.success;
        }
        return result;
    }

    /**
     * 添加字典
     *
     * @param dicName
     * @param dicType
     * @return
     */
    @RequestMapping(value = "dicadd")
    @ResponseBody
    public String addData(@RequestParam("dicName") String dicName, @RequestParam("dicType") String dicType) {
        //直接写入数据
        String result = ResponseCode.error;
        if (dicName != "") {
            dictionary model = new dictionary();
            model.setDictionaryid(StringUtil.getUUID());
            model.setDictionaryname(dicName);
            model.setDictypeid(dicType);

            dictionaryService.insert(model);
            result = ResponseCode.success;
        }
        return result;
    }

    /**
     * 获取字典原始数据和字典类型名称，封装此成新的list
     *
     * @param dicList
     * @return
     */
    private List<DicWithTypeName> getResultDataList(List<dictionary> dicList) {
        List<DicWithTypeName> resList = new ArrayList<DicWithTypeName>();
        for (dictionary item :
                dicList) {
            DicWithTypeName obj = new DicWithTypeName();//数据处理
            String typeName = dictionaryTypeService.selectByPrimaryKey(item.getDictypeid()).getDictypename();

            obj.setDictionaryid(item.getDictionaryid());
            obj.setDictionaryname(item.getDictionaryname());
            obj.setDictypeid(item.getDictypeid());
            obj.setDictypename(typeName);

            resList.add(obj);
        }
        return resList;
    }
}
