
	package com.hiideals


	import com.hiideals.loan.BirthdayList
import grails.transaction.Transactional
	import org.grails.datastore.mapping.validation.ValidationException
	import org.slf4j.Logger
import org.slf4j.LoggerFactory
	
	@Transactional
	class LongTermLoanService {
	
		// Logger instance for the service
		private static final Logger log = LoggerFactory.getLogger(LongTermLoanService.class)
	
		/**
		 * Fetches all short-term loans for a specific admin.
		 * @param adminId The ID of the current admin.
		 * @return List of short-term loans associated with the admin.
		 */
		def fetchLoansByAdmin(Long adminId) {
			if (!adminId) {
				log.warn("Admin ID is null. Cannot fetch loans.")
				throw new IllegalArgumentException("Admin ID cannot be null.")
			}
	
			def loans = BirthdayList.findAllByAdminId(adminId)
			log.debug("Fetched ${loans.size()} loans for Admin ID: $adminId")
			return loans
		}
	
		/**
		 * Creates and saves a new short-term loan.
		 * @param params The parameters for the new loan.
		 * @return The saved ShortTermLoan instance.
		 * @throws ValidationException if validation fails during saving.
		 */
		def createLoan(Map params) {
			if (!params) {
				log.warn("Loan parameters are null or empty.")
				throw new IllegalArgumentException("Loan parameters cannot be null.")
			}
	
			def longTermLoanInstance = new BirthdayList(params)
	
			if (!longTermLoanInstance.validate()) {
				log.warn("Validation failed for LongTermLoan: ${longTermLoanInstance.errors}")
				throw new ValidationException("Validation failed.", longTermLoanInstance.errors)
			}
	
			longTermLoanInstance.save(flush: true)
			log.info("Successfully created ShortTermLoan with ID: ${longTermLoanInstance.id}")
			return longTermLoanInstance
		}
	}
	

