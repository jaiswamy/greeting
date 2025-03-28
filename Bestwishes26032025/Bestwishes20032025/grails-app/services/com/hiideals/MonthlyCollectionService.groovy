package com.hiideals

import com.hiideals.io.Membercreation
import com.hiideals.loan.AnniversaryList
import grails.transaction.Transactional
import javax.validation.ValidationException

@Transactional
class MonthlyCollectionService {

    def list(params) {
        AnniversaryList.list(params)
    }

    def count() {
        AnniversaryList.count()
    }

    def get(Long id) {
        AnniversaryList.findById(id)
    }

    def save(AnniversaryList monthlyCollection) {
        if (!monthlyCollection.validate()) {
            throw new ValidationException("Validation failed for Monthly Collection", monthlyCollection.errors)
        }
        monthlyCollection.save(flush: true)
    }

    def delete(Long id) {
        def monthlyCollection = get(id)
        if (monthlyCollection) {
            monthlyCollection.delete(flush: true)
            return true
        }
        return false
    }

    /**
     * Calculate EMI based on loan amount, interest rate, and duration.
     */
    def calculateEmi(Double loanAmount, Double interestRate, Integer durationInMonths) {
        if (loanAmount && interestRate && durationInMonths) {
            def monthlyRate = interestRate / 12 / 100
            def emi = (loanAmount * monthlyRate * Math.pow(1 + monthlyRate, durationInMonths)) /
                    (Math.pow(1 + monthlyRate, durationInMonths) - 1)
            return emi
        }
        return 0.0
    }

    /**
     * Fetch all monthly collections for a specific member.
     */
    def findByMember(Long memberId) {
        Membercreation member = Membercreation.findById(memberId)
        if (member) {
            return AnniversaryList.findAllByMemberDeatail(member)
        }
        return []
    }
}
