package com.hiideals

import com.hiideals.io.Membercreation

class MembercreationService {
	
		String generateMembershipNo() {
			def lastMember = Membercreation.find("from Membercreation order by membershipNo desc")
			if (lastMember?.membershipNo) {
				// Extract the last 3 digits, increment by 1
				String lastSequence = lastMember.membershipNo[-3..-1]
				Integer newSequence = (lastSequence as Integer) + 1
				return String.format("MB%03d", newSequence)
			} else {
				// Start from MB001 if no records exist
				return "MB001"
			}
		}
	
		def saveMembercreation(Membercreation membercreation) {
			membercreation.membershipNo = generateMembershipNo()
			membercreation.save(flush: true)
		}
	}
	
