package io.rainforest.banana.client1.web.demo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("body")
@Tag(name = "body参数")
public class BodyController {

   @Operation(summary = "普通body请求")
   @PostMapping("/body")
   public ResponseEntity<String> body(String fileResp){
       return ResponseEntity.ok(fileResp);
   }

   @Operation(summary = "普通body请求+Param+Header+Path")
   @Parameters({
           @Parameter(name = "id",description = "文件id",in = ParameterIn.PATH),
           @Parameter(name = "token",description = "请求token",required = true,in = ParameterIn.HEADER),
           @Parameter(name = "name",description = "文件名称",required = true,in=ParameterIn.QUERY)
   })
   @PostMapping("/bodyParamHeaderPath/{id}")
   public ResponseEntity<String> bodyParamHeaderPath(@PathVariable("id") String id, @RequestHeader("token") String token, @RequestParam("name")String name, @RequestBody String fileResp){
       return ResponseEntity.ok(fileResp+""+",receiveName:"+name+",token:"+token+",pathID:"+id);
   }
}
