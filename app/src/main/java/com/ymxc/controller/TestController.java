package com.ymxc.controller;

import com.ymxc.template.PulsarProducerService;
import com.ymxc.webmvc.Restful;
import lombok.RequiredArgsConstructor;
import org.apache.pulsar.client.api.PulsarClientException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final PulsarProducerService producerService;

    @GetMapping("/test")
    public Restful test() {
        try {
            for (int i = 0; i < 10000; i++) {
                producerService.createBuilder()
                        .topic("dev-topic")
                        .sendAsync("{\"opter_name\":\"麻美丽\",\"input\":{\"data\":{\"psn_no\":\"21000021010000001000551743\",\"med_type\":\"11\",\"medfee_sumamt\":132,\"psn_setlway\":\"01\",\"mdtrt_id\":\"157699763\",\"chrg_bchno\":\"ZD2023022709535434\",\"acct_used_flag\":\"1\",\"exp_content\":{\"dise_reim_mon\":\"\",\"deve_flag\":\"\",\"otp_reim_flag\":\"\"},\"insutype\":\"310\",\"mdtrt_cert_type\":\"03\",\"mdtrt_cert_no\":\"A0233690X\"},\"insuplcAdmdvs\":\"210106\"},\"fixmedins_name\":\"沈阳肤康皮肤病医院\",\"sign_no\":\"\",\"opter\":\"1453941296463904\",\"fixmedins_code\":\"H21010400145\",\"infno\":\"2206A\",\"dev_no\":\"\",\"dev_safe_info\":\"\",\"inf_time\":\"2023-02-27 09:53:54\",\"recer_sys_code\":\"YMXCHIS\",\"insuplc_admdvs\":\"210106\",\"infver\":\"V1.0\",\"opter_type\":\"1\",\"cainfo\":\"045f9afd287c256476648c65eb6c61c015cdfc12776e384d7851aae7cd674282b68a90661bbc8565ae10ec79ce6a8d4c5b202ee3a64520618969b9cb1f35f9fcf6feef09c555ddc5e419a0a52dfb9b7cd2c2b509ef9b4b7b69183d7b8cffd63b3efe42d954d98ca734232ab1483525c5375625cfd690054df58274568c3369ff1a\",\"signtype\":\"\",\"msgid\":\"202302270953542206A82\",\"mdtrtarea_admvs\":\"210100\"}");
            }
        } catch (PulsarClientException e) {
            throw new RuntimeException(e);
        }
        return Restful.SUCCESS().object("SUCCESS").build();
    }

}
