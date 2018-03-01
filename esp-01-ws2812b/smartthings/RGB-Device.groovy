/**
 *  neopixel RGB Light
 */

import groovy.json.JsonSlurper


metadata {
	definition( name: "Generic UPnP Device", namespace: "smartthings", author: "cipherforge", ocfDeviceType: "oic.d.light") {
		capability "Switch Level"
		capability "Color Control"
		capability "Color Temperature"
		capability "Switch"
		capability "Refresh"
		capability "Actuator"
		capability "Sensor"
		command "reset"
	}

	simulator {
	}

   multiAttributeTile(name:"switch", type: "lighting", width: 6, height: 4, canChangeIcon: true) {
    
      tileAttribute ("device.switch", key: "PRIMARY_CONTROL" ) {
        attributeState "on", label:'${name}', action:"switch.off", icon:"st.lights.philips.hue-single", backgroundColor:"#00a0dc", nextState:"off"
        attributeState "off", label:'${name}', action:"switch.on", icon:"st.lights.philips.hue-single", backgroundColor:"#ffffff", nextState:"on"

      }
	 /* tileAttribute ("device.level", key: "SLIDER_CONTROL") {
				attributeState "level", action:"switch level.setLevel", value: "80"
	  }
      */
	  tileAttribute ("device.color", key: "COLOR_CONTROL") {
				attributeState "color", action:"setColor"
      }
   }
    
    valueTile("spacer0", "device.integer", width: 3, height: 1) {
        state "val", label:'Device Presets', defaultState: true
    }

	standardTile("party", "device.party", inactiveLabel: false, decoration: "flat") {
		state "default", label:"Party Mode", action:"party", icon:"st.lights.philips.hue-single"
	}
    
	standardTile("reset", "device.reset", inactiveLabel: false, decoration: "flat") {
		state "default", label:"Reset Color", action:"reset", icon:"st.lights.philips.hue-single"
	}
    
	standardTile("refresh", "device.switch", inactiveLabel: false, decoration: "flat") {
		state "default", label:"", action:"refresh.refresh", icon:"st.secondary.refresh"
	}
    
   main(["switch"])
   details(["switch", "spacer0", "party", "reset", "refresh"])
    
}

def poll() {
	log.debug "Executing 'poll' ${getHostAddress()}"
}
