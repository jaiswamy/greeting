package com.hiidelas

class PublicCertificateController {

    def viewBirthdayCertificate(String fileName) {
        String filePath = "F:/backup/BestWishes/BestWishes/uploadbirthdaycertificate/${fileName}"
        serveCertificate(filePath)
    }

    def viewAnniversaryCertificate(String fileName) {
        String filePath = "F:/backup/BestWishes/BestWishes/uploadanniversarycertificate/${fileName}"
        serveCertificate(filePath)
    }

    private void serveCertificate(String filePath) {
        File file = new File(filePath)
        if (file.exists()) {
            response.contentType = "application/pdf"
            response.setHeader("Content-Disposition", "inline; filename=${file.name}")
            response.outputStream << file.bytes
        } else {
            render(status: 404, text: "Error: File not found!")
        }
    }
}