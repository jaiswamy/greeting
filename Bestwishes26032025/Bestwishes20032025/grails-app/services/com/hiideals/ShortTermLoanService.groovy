package com.hiideals

import com.hiideals.loan.ImageUpload

import grails.transaction.Transactional
import org.grails.datastore.mapping.validation.ValidationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Transactional
class ShortTermLoanService {

    // Logger instance for the service
    private static final Logger log = LoggerFactory.getLogger(ShortTermLoanService.class)

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

        def loans = ImageUpload.findAllByAdminId(adminId)
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

        def shortTermLoanInstance = new ImageUpload(params)

        if (!shortTermLoanInstance.validate()) {
            log.warn("Validation failed for ShortTermLoan: ${shortTermLoanInstance.errors}")
            throw new ValidationException("Validation failed.", shortTermLoanInstance.errors)
        }

        shortTermLoanInstance.save(flush: true)
        log.info("Successfully created ShortTermLoan with ID: ${shortTermLoanInstance.id}")
        return shortTermLoanInstance
    }
}
