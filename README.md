# ALK_UPDATED02
Aadhar-Linkage-MicroService


{
  "action": "INSERT",
  "source": "AADHAAR",
  "aadhaar": {
    "aadhaarNumber": "123456789012",
    "parentGuardianName": "Rama Rao",
    "mobile": "9876543210",
    "email": "testaadhaar@example.com",
    "person": {
      "forename": "Samba",
      "middleName": "S",
      "lastName": "Rao",
      "dob": "1999-05-12",
      "gender": "M",
      "address": "Guntur, Andhra Pradesh"
    }
  }
}


{
  "action": "UPDATE",
  "source": "AADHAAR",
  "aadhaar": {
    "aadhaarNumber": "123456789012",
    "parentGuardianName": "Updated Rao",
    "mobile": "9998887776",
    "email": "updatedaadhaar@example.com",
    "person": {
      "forename": "Samba",
      "middleName": "K",
      "lastName": "Rao",
      "dob": "1999-05-12",
      "gender": "M",
      "address": "Updated Address, AP"
    }
  }
}


{
  "action": "DELETE",
  "source": "AADHAAR",
  "aadhaar": {
    "aadhaarNumber": "123456789012",
    "person": {
      "forename": "Samba",
      "lastName": "Rao",
      "dob": "1999-05-12",
      "gender": "M",
      "address": ""
    }
  }
}


{
  "action": "INSERT",
  "source": "PAN",
  "pan": {
    "panNumber": "ABCDE1234F",
    "fatherName": "Krishna Rao",
    "motherName": "Lakshmi",
    "phone": "9876501234",
    "email": "panuser@example.com",
    "aadhaarLinked": "123456789012",
    "person": {
      "forename": "Samba",
      "middleName": "",
      "lastName": "Rao",
      "dob": "1999-05-12",
      "gender": "M",
      "address": "Guntur, AP"
    }
  }
}


{
  "action": "UPDATE",
  "source": "PAN",
  "pan": {
    "panNumber": "ABCDE1234F",
    "fatherName": "Updated Father",
    "motherName": "Updated Mother",
    "phone": "9991112233",
    "email": "updatedpan@example.com",
    "aadhaarLinked": "123456789012",
    "person": {
      "forename": "Samba",
      "middleName": "NewMiddle",
      "lastName": "Rao",
      "dob": "1999-05-12",
      "gender": "M",
      "address": "Updated Address, AP"
    }
  }
}

{
  "action": "INSERT",
  "source": "VOTER",
  "voter": {
    "epicNumber": "AP1234567890",
    "relativeName": "Rama Rao",
    "electoralDistrict": "Guntur",
    "assemblyConstituency": "Mangalagiri",
    "partNumber": "45",
    "photograph": "SGVsbG8gV29ybGQ=", 
    "person": {
      "forename": "Samba",
      "middleName": "",
      "lastName": "Rao",
      "dob": "1999-05-12",
      "gender": "M",
      "address": "Guntur, AP"
    }
  }
}


{
  "action": "INSERT",
  "source": "DRIVING",
  "driving": {
    "dlNumber": "DL-0420110143210",
    "licenseClass": "MCWG",
    "height": "172",
    "bloodType": "O+",
    "issueDate": "2020-01-01",
    "expiryDate": "2030-01-01",
    "person": {
      "forename": "Samba",
      "middleName": "",
      "lastName": "Rao",
      "dob": "1999-05-12",
      "gender": "M",
      "address": "Guntur, AP"
    }
  }
}
