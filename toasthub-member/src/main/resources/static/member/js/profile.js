/**
 * @author Edward H. Seufert
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

Profile.prototype = Object.create(toastHubProfile.prototype);
Profile.prototype.constructor = Profile;

toastHub.registerController("profile",new Profile("profile"));
toastHub.registerWidget("profile",new Profile("profile"));

function Profile(instanceName){
	toastHubProfile.call(this,instanceName,this);
	this.controllerName = "profile";
	var self = this;
	
	// Override the default setup
	
	
	

}; // profile