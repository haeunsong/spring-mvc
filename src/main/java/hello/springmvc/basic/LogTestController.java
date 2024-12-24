package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// 그냥 @Controller 는 반환값이 String 이면, 뷰 이름으로 인식된다.
// @RestController 는 뷰가 아니라 실제 String 을 바로 반환한다. (@ResponseBody 와 관련있음)
@RestController
@Slf4j
public class LogTestController {

    // private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        log.trace(" trace log={}",name);
        log.debug(" debug log={}",name);
        log.info(" info log={}",name);
        log.warn(" warn log={}",name);
        log.error(" error log={}",name);

        return "ok";
    }

}
