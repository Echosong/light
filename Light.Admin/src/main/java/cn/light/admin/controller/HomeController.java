package cn.light.admin.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.light.common.annotation.NoPermission;
import cn.light.common.enums.BaseEnum;
import cn.light.common.util.DtoMapper;
import cn.light.entity.cache.SmsCache;
import cn.light.entity.cache.SmsCacheRepository;
import cn.light.entity.entity.SysUser;
import cn.light.entity.repository.UserRepository;
import cn.light.packet.dto.user.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.*;

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
    private SmsCacheRepository smsCacheRepository;

    @Value("${app.version}")
    private String appVersion;

    @Value("${app.build.time}")
    private String buildTime;



    @GetMapping("/home/unreadCount")
    @Operation(summary = "获取未读消息数量")
    public Integer getUnreadCount(){
        return 10;
    }


    @GetMapping("/test")
    @NoPermission
    public String test(){
        Optional<SmsCache> byId = smsCacheRepository.findById("18317033205");
        if (byId.isPresent()) {
            return byId.get().getContent();
        }else{
            SmsCache smsCache = SmsCache.builder().id("18317033205").content("123654").build();
            smsCacheRepository.save(smsCache);
            return "success";
        }
    }

    //获取版本号
    @GetMapping("/version")
    @Operation(summary = "获取后台接口版本号")
    public Map<String, String> version() {
        Map<String, String> versionMap = new HashMap<>();
        versionMap.put("version", appVersion);
        Date localDateTime = DateUtil.parse(buildTime);
        String format = DateUtil.format(DateUtil.offsetHour(localDateTime, 8), "yyyyMMdd HH:mm:ss");
        versionMap.put("buildTime", format );
        return versionMap;
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
        return new HashMap<>(5);
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
