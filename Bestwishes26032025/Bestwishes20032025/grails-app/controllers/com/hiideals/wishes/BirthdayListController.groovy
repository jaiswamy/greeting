 package com.hiideals.wishes

import grails.plugin.springsecurity.annotation.Secured

import grails.validation.ValidationException
import java.time.LocalDate
import org.slf4j.Logger
import com.hiideals.SendWhatsAppImage
import com.hiideals.WhatsappLink
import org.slf4j.LoggerFactory
import com.hiideals.MembercreationService
import com.hiideals.io.Membercreation
import com.springapp.Admin
import com.springapp.SecUser

@Secured(["ROLE_SUPERADMIN", "ROLE_ADMIN", "ROLE_USER", "ROLE_VCADMIN", "ROLE_DUSER"])
class BirthdayListController {
    MembercreationService membercreationService
    def userService

    private static final Logger log = LoggerFactory.getLogger(BirthdayListController)

    def index() {
        def birthdayListInstanceList = BirthdayList.findAllByAdminId(userService.getCurrentAdmin())
        [birthdayListInstanceList: birthdayListInstanceList]
    }

	
	/*def fetchTodayBirthdays() {
    def today = new Date().clearTime()
    def adminId = userService.getCurrentAdmin()

    // Fetch members whose birthday matches today (ignoring year)
    def birthdayMembers = Membercreation.createCriteria().list {
        eq('adminId', adminId)
        sqlRestriction("DAY(date_of_birth) = DAY(?) AND MONTH(date_of_birth) = MONTH(?)", [today, today])
    }

    log.debug("Today's Birthdays: ${birthdayMembers}")

    render(template: '/birthdayList/birthdaysTable', model: [birthdayMembers: birthdayMembers])
	}*/


	/*def fetchTodayBirthdays() {
		def today = new Date().clearTime()
		def adminId = userService.getCurrentAdmin()
	
		// Fetch members whose birthday matches today (ignoring year)
		def birthdayMembers = Membercreation.createCriteria().list {
			eq('adminId', adminId)
			sqlRestriction("DAY(date_of_birth) = DAY(?) AND MONTH(date_of_birth) = MONTH(?)", [today, today])
		}
	
		log.debug("Today's Birthdays: ${birthdayMembers}")
	
		// WhatsApp message details
		String imageUrl = "https://whatsappdata.s3.ap-south-1.amazonaws.com/userMedia/4c07fe24771249c343e70c32289c1192/birthday_new.jpeg"
	
		// Send WhatsApp messages to each member
		birthdayMembers.each { member ->
			if (member?.contactNo) {
				String phoneNumber = "91" + member.contactNo
				try {
					SendWhatsAppImage.sendMessage(imageUrl, phoneNumber)
					log.debug("WhatsApp message sent to: ${phoneNumber}")
				} catch (Exception e) {
					log.error("Failed to send WhatsApp message to ${phoneNumber}: ${e.message}", e)
				}
			} else {
				log.warn("Skipping WhatsApp message. No contact number for: ${member?.name}")
			}
		}
	
		render(template: '/birthdayList/birthdaysTable', model: [birthdayMembers: birthdayMembers])
	}*/
	
	
	
	def fetchTodayBirthdays() {
		def today = new Date().clearTime()
		def adminId = userService.getCurrentAdmin()
	
		log.debug("Admin ID: ${adminId}")
		log.debug("Today's Date: ${today}")
	
		// Fetch members whose birthday matches today (ignoring year)
		def birthdayMembers = Membercreation.createCriteria().list {
			eq('adminId', adminId)
			sqlRestriction("DAY(date_of_birth) = DAY(?) AND MONTH(date_of_birth) = MONTH(?)", [today, today])
		}
	
		log.debug("Today's Birthdays: ${birthdayMembers}")  // Check if members are fetched
	
		if (!birthdayMembers) {
			log.error("No birthday members found.")
			render "Failed to fetch birthdays. Please try again."
			return
		}
	
		// WhatsApp message details
		String imageUrl = "https://whatsappdata.s3.ap-south-1.amazonaws.com/userMedia/4c07fe24771249c343e70c32289c1192/birthday_new.jpeg"
	
		// Send WhatsApp messages to each member
		birthdayMembers.each { member ->
			if (member?.contactNo && member?.membershipNo) {
				String phoneNumber = "91" + member.contactNo
				String membershipNo = member.membershipNo.toString() // Ensure it's a string
				String birthdaypdfLink = member.birthdaypdfLink?.toString() // Ensure it's not null
				
				try {
					WhatsappLink.sendMessage("John Doe", "https://example.com/certificate.pdf", phoneNumber);
					//SendWhatsAppImage.sendMessage(imageUrl, membershipNo, phoneNumber)
					log.debug("WhatsApp message sent to: ${phoneNumber}, Membership No: ${membershipNo}")
				} catch (Exception e) {
					log.error("Failed to send WhatsApp message to ${phoneNumber}: ${e.message}", e)
				}
			} else {
				log.warn("Skipping WhatsApp message. No contact number or membership number for: ${member?.name}")
			}
		}
	
		render(template: '/birthdayList/birthdaysTable', model: [birthdayMembers: birthdayMembers])
	}
	
    
}

