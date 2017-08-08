/*
 * Copyright (C) 2016 The ToastHub Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.toasthub.member.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.toasthub.core.general.api.View;
import org.toasthub.core.general.handler.ServiceProcessor;
import org.toasthub.core.general.model.BaseEntity;
import org.toasthub.core.general.model.RestRequest;
import org.toasthub.core.general.model.RestResponse;
import org.toasthub.core.general.model.ServiceCrawler;
import org.toasthub.core.general.service.EntityManagerMainSvc;
import org.toasthub.core.general.service.UtilSvc;

import com.fasterxml.jackson.annotation.JsonView;


@RestController()
@RequestMapping("/api/member")
public class MemberWS {

	@Autowired	EntityManagerMainSvc entityManagerMainSvc;
	@Autowired UtilSvc utilSvc;
	@Autowired ServiceCrawler serviceLocator;

	@JsonView(View.Member.class)
	@RequestMapping(value = "callService", method = RequestMethod.POST)
	public RestResponse callService(@RequestBody RestRequest request) {
	
		RestResponse response = new RestResponse();
		// set defaults
		utilSvc.setupDefaults(request);
		// validate request
		
		// response
		response.addParam(BaseEntity.CONTEXTPATH, entityManagerMainSvc.getAppName());
				
		// call service locator
		ServiceProcessor x = serviceLocator.getService("MEMBER", (String) request.getParams().get(BaseEntity.SERVICE),
				(String) request.getParam(BaseEntity.SVCAPIVERSION), (String) request.getParam(BaseEntity.SVCAPPVERSION),
				entityManagerMainSvc.getAppDomain());
		// process 
		if (x != null) {
			x.process(request, response);
		} else {
		
		}
		
		return response;
	}
	
	/*
	@RequestMapping("/uploadService")
	public String uploadService(MultipartFormDataInput input){
		RestResponse response = new RestResponse();
		RestRequest request = new RestRequest();
		input.getParts();
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		try {
			String paramString = uploadForm.get(BaseEntity.PARAMS).get(0).getBodyAsString();
			Map<String,Object> paramObj = new Gson().fromJson(paramString,Map.class);
			Map<String,Object> params = (Map<String,Object>) paramObj.get(BaseEntity.PARAMS);
			request.setParams(params);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// set defaults
		utilSvc.setupDefaults(request);
		request.addParam("uploadForm", uploadForm);
		// call service locator

		ServiceProcessor x = serviceLocator.getService("MEMBER", (String) request.getParams().get(BaseEntity.SERVICE),
				(String) request.getParam(BaseEntity.SVCAPIVERSION), (String) request.getParam(BaseEntity.SVCAPPVERSION),
				entityManagerSvc.getAppDomain());
		// process 
		if (x != null) {
			x.process(request, response);
		} else {
		
		}
		
		return utilSvc.writeResponseMember(response);
	}
	
	@POST
	@Path("/downloadService")
	@Consumes({"application/json", "application/xml"})
	@Produces("image/png")
	public Response downloadService(RestRequest request){
		RestResponse response = new RestResponse();
		// set defaults
		utilSvc.setupDefaults(request);

		
		// call service locator
		ServiceProcessor x = serviceLocator.getService("MEMBER", (String) request.getParams().get(BaseEntity.SERVICE),
				(String) request.getParam(BaseEntity.SVCAPIVERSION), (String) request.getParam(BaseEntity.SVCAPPVERSION),
				entityManagerSvc.getAppDomain());
		// process 
		if (x != null) {
			x.process(request, response);
		} else {
		
		}
		ResponseBuilder outResponse = null;
		AttachmentMeta attachment = (AttachmentMeta) response.getParam("attachment");
		if ( "FULLRES".equals(request.getParam("fileType")) ){
			if (attachment.getData() != null && attachment.getData().getData() != null) {
				outResponse = Response.ok((Object) attachment.getData().getData());
			}
		} else {
			if (attachment.getThumbNail() != null && attachment.getThumbNail().getData() != null){
				outResponse = Response.ok((Object) attachment.getThumbNail().getData());
			}
		}
		
		if (outResponse != null){
			outResponse.header("Content-Disposition","attachment; filename="+attachment.getFileName());
			return outResponse.build();
		} else {
			outResponse = Response.noContent();
			return null;
		}
	}
	*/
}
