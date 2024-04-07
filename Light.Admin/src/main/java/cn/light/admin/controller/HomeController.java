package cn.light.admin.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.annotation.NoPermission;
import cn.light.entity.entity.SysOrder;
import cn.light.entity.mapper.OrderMapper;
import cn.light.entity.repository.OrderRepository;
import cn.light.packet.dto.order.SumAmountByCompanyDTO;
import cn.light.packet.dto.user.UserDTO;
import cn.light.common.enums.BaseEnum;
import cn.light.common.util.DtoMapper;
import cn.light.entity.entity.SysUser;
import cn.light.entity.mapper.PermissionMapper;
import cn.light.entity.repository.RoleRepository;
import cn.light.entity.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.sqm.mutation.internal.cte.CteInsertStrategy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 后台首页接口处理
 *
 * @author Administrator
 */
@Tag(name = "接口处理")
@RestController
@Slf4j
@Validated
public class HomeController extends BaseController {

    @Resource
    private  UserRepository userRepository;
    @Resource
    private  RoleRepository roleRepository;

    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private OrderMapper orderMapper;
    @Resource
    private OrderRepository orderRepository;


    private final String format = "yyyy-MM-dd";

    @GetMapping("/test/{id}")
    @NoPermission
    public String test(@PathVariable Integer id){
        return id.toString();
    }

    /**
     * 获取所有项目信息，主要市拿相关编号配置给前端使用
     *
     * @return 项目列表
     */
    @GetMapping("/index")
    @Operation(summary = "获取所有项目信息")
    @NoPermission
    public Map<String, Object> index() {
        Map<String, Object> returnMap = new HashMap<>(5);

        Map<String, Object> orderCountMap = new HashMap<>(3);

        String currentWeek =  DateUtil.format(DateUtil.beginOfWeek(new Date()), format);
        String lastWeek = DateUtil.format(DateUtil.beginOfWeek(DateUtil.offsetWeek(new Date(), -1)), format);

        orderCountMap.put("上周保费", orderMapper.selectSumAmount(lastWeek,  currentWeek,null));

        String currentMouth = DateUtil.format( DateUtil.beginOfMonth(new Date()), format);

        String now = DateUtil.format(new Date(), format);
        orderCountMap.put("本月月保费", orderMapper.selectSumAmount(currentMouth, now,   null));

        //DateUtil 获取上个月时间
        String lastMouth = DateUtil.format(DateUtil.beginOfMonth(DateUtil.offsetMonth(new Date(), -1)), format);
        //上个月
        orderCountMap.put("上月保费", orderMapper.selectSumAmount(currentMouth, lastMouth, null));

        returnMap.put("交易统计", orderCountMap);

        //饼图
        List<SumAmountByCompanyDTO> sumAmountByCompanyResults = orderMapper.SumAmountByCompany();
        Map<String, BigDecimal> pie = new HashMap<>(3);
        Set<String> companyNames = sumAmountByCompanyResults.stream().map(SumAmountByCompanyDTO::getChannelName).collect(Collectors.toSet());
        for (String companyName : companyNames) {
            BigDecimal amount = sumAmountByCompanyResults.stream().filter(t -> t.getChannelName().equals(companyName))
                    .map(SumAmountByCompanyDTO::getAmount)
                    .findFirst()
                    .orElse(BigDecimal.ZERO);
            if(amount.compareTo(BigDecimal.ZERO) > 0) {
                pie.put(companyName, amount);
            }
        }
        returnMap.put("金额统计饼图", pie);


        Date beginWeek = DateUtil.beginOfWeek(new Date());
        //矩形图
        List<SysOrder> byOrderTimeAfter = orderRepository.findByOrderTimeAfter(beginWeek);
        Map<String, BigDecimal> listMap = new HashMap<>(7);
        for (int i = 0; i < 7; i++) {
            String currentDate = DateUtil.format(DateUtil.offsetDay(DateUtil.beginOfWeek(new Date()), i), format);
            listMap.put(currentDate, BigDecimal.ZERO);
            BigDecimal reduce = BigDecimal.ZERO;
            BigDecimal threeClassReduce = byOrderTimeAfter.stream().filter(t -> DateUtil.format(t.getOrderTime(), format).equals(currentDate))
                    .map(SysOrder::getThreeClassPrice).reduce(BigDecimal.ZERO, BigDecimal::add);


            //四类
            BigDecimal fourClassReduce = byOrderTimeAfter.stream().filter(t -> DateUtil.format(t.getOrderTime(), format).equals(currentDate))
                    .map(SysOrder::getFourClassPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
            //五类
            BigDecimal fiveClassReduce = byOrderTimeAfter.stream().filter(t -> DateUtil.format(t.getOrderTime(), format).equals(currentDate))
                    .map(SysOrder::getFiveClassPrice).reduce(BigDecimal.ZERO, BigDecimal::add);

            listMap.put(currentDate, threeClassReduce.add(fourClassReduce).add(fiveClassReduce));

        }
        returnMap.put("周业绩统计矩形图", listMap);

        return returnMap;
    }

    /**
     * 注册用户信息
     *
     * @param userDTO 注册新用户
     */
    @PostMapping("/reg")
    @Operation(summary = "注册用户")
    public void reg(@RequestBody UserDTO userDTO) {
        SysUser userDb = DtoMapper.convert(userDTO, SysUser.class);
        //新注册用户暂时给0
        userDb.setState(0);
        userRepository.save(userDb);
    }

    /**
     * 约定通用方法（表得某个字段需要枚举时候，
     * 那么用 (表名+字段+Enum) 比如
     * UserSateEnum 就表示 user 模型下 state 字段 对应得枚举）
     */
    @GetMapping("/getEnums")
    @Operation(summary = "(实体+字段+Enum) UserSateEnum ")
    @NoPermission
    public List<Map<String, Object>> getEnums(@RequestParam(value = "enumName") @Valid @NotBlank String enumName) throws ClassNotFoundException {
        return BaseEnum.toMap(StrUtil.upperFirst(enumName));
    }

}
