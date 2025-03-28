<style>
    .styled-input {
        background-color: #f9f9f9;
        border: 2px solid #007bff;
        border-radius: 0.5rem;
        padding: 0.5rem 1rem;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .styled-input:focus {
        outline: none;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }
</style>
<div class="margin-bottom-20"></div>
<g:form controller="membercreation" enctype="multipart/form-data"
	name="validateip" class="horizontal-form">
	<!-- Contact Name -->
    <div class="col-md-3">
        <div class="fieldcontain ${hasErrors(bean: usersInstance, field: 'contactName', 'error')}">
            <label for="contactName" class="form-label fw-bold">
                <g:message code="user.contactName.label" default="Contact Name" />
            </label>
            <g:if test="${params?.action != 'adminEdit'}">
                <g:textField name="contactName" class="form-control spinner input-circle styled-input"
                    value="${usersInstance?.contactName}" required="true" style="background-color: #f9f9f9; border-width: 2px;"/>
            </g:if>
            <g:else>
                <input name="contactName" class="form-control spinner input-circle styled-input"
                    value="${usersInstance?.contactName}" style="background-color: #f9f9f9; border-width: 2px;"/>
            </g:else>
        </div>
    </div>
		
			<div class="col-md-3">
				<div
					class="fieldcontain ${hasErrors(bean: usersInstance, field: 'username', 'error')} ">
					
					<label for="username"> <g:message
							code="user.username.label" default="Username" />

					</label>
					<g:if test="${params?.action !="adminEdit"}">
						<g:textField name="username" class="form-control spinner input-circle styled-input"
							value="${usersInstance?.username}" required="true" style="background-color: #f9f9f9; border-width: 2px;"/>
					</g:if>
					<g:else>
						<input name="username" class="form-control spinner input-circle styled-input"
							value="${usersInstance?.username}" style="background-color: #f9f9f9; border-width: 2px;" />
					</g:else>
				</div>
			</div>

			<div class="col-md-3">
				<div
					class="fieldcontain ${hasErrors(bean: usersInstance, field: 'password', 'error')} ">
					<label for="password"> <g:message
							code="user.password.label" default="Password" />

					</label>
					<g:if test="${params?.action !="adminEdit"}">
						<g:textField name="password" class="form-control spinner input-circle styled-input"
							value="${usersInstance?.password}" required="true" style="background-color: #f9f9f9; border-width: 2px;" />
					</g:if>
					<g:else>
						<input name="password" class="form-control spinner input-circle styled-input"
							value="${usersInstance?.password}" style="background-color: #f9f9f9; border-width: 2px;"/>
					</g:else>
				</div>
			</div>
			<!-- Contact No -->
<div class="col-md-3">
    <div class="fieldcontain ${hasErrors(bean: usersInstance, field: 'contactNo', 'error')}">
        <label for="contactNo">
            <g:message code="user.contactNo.label" default="Contact No." />
        </label>
        <input type="tel" name="contactNo" id="contactNo" class="form-control spinner input-circle styled-input"
            value="${usersInstance?.contactNo}" 
            required="true" 
            maxlength="10" 
            pattern="^\d{10}$" 
            title="Please enter exactly 10 digits for the contact number." style="background-color: #f9f9f9; border-width: 2px;"/>
    </div>
</div>

    <!-- Date of Birth -->
        <div class="col-md-3">
            <div class="fieldcontain ${hasErrors(bean: usersInstance, field: 'dateOfBirth', 'error')}">
                <label for="dateOfBirth" class="form-label fw-bold">
                    <g:message code="user.dateOfBirth.label" default="Date of Birth" />
                </label><br>
                <g:if test="${params?.action != 'adminEdit'}">
                    <g:datePicker name="dateOfBirth" class="form-control rounded shadow-sm styled-input" value="${usersInstance?.dateOfBirth}" precision="day" required="true" />
                </g:if>
                <g:else>
                    <input type="date" name="dateOfBirth" class="form-control rounded shadow-sm styled-input" value="${usersInstance?.dateOfBirth}" />
                </g:else>
            </div>
        </div>

        <!-- Date of Birth -->
        <div class="col-md-3">
            <div class="fieldcontain ${hasErrors(bean: usersInstance, field: 'dateOfAnniversary', 'error')}">
                <label for="dateOfAnniversary" class="form-label fw-bold">
                    <g:message code="user.dateOfAnniversary.label" default="Date of Anniversary" />
                </label><br>
                <g:if test="${params?.action != 'adminEdit'}">
                    <g:datePicker name="dateOfAnniversary" class="form-control rounded shadow-sm styled-input" value="${usersInstance?.dateOfAnniversary}" precision="day" required="true" />
                </g:if>
                <g:else>
                    <input type="text" class="form-control rounded shadow-sm styled-input" name="dateOfAnniversary" id="dateOfAnniversary" style="border:1px solid black;" placeholder="mm/dd/yyyy" value="${formatDate(format:'dd/MM/yyyy',date:usersInstance?.dateOfAnniversary)}" />
                </g:else>
            </div>
        </div>
    
    <div class="col-md-3">
    <div class="fieldcontain ${hasErrors(bean: usersInstance, field: 'address', 'error')}">
        <label for="address">
            <g:message code="user.address.label" default="Address" />
        </label>
        <g:textField name="address" class="form-control spinner input-circle styled-input" value="${usersInstance?.address ?: ''}" required="true" style="background-color: #f9f9f9; border-width: 2px;"/>
    </div>
</div>
    
		<g:if test="${params?.action !="userEdit"}">
			<div class="row">
				
				<div class="col-md-3">
					<label>Contact Upload Image</label> <input type="file" class="form-control spinner input-circle styled-input"
						name="file_1" value="" style="background-color: #f9f9f9; border-width: 2px;">
				</div>
			</div>
			<br>
			<g:actionSubmit action="saveUser" class="btn btn-primary btn-md px-5 rounded-pill shadow-sm " style="margin-left: 20px;"
				value="Save Details"></g:actionSubmit>
		</g:if>
		
		<g:else>
		<div class="col-md-3">
					<label>Contact Upload Image</label> <input type="file" class="form-control spinner input-circle styled-input"
						name="file_1" value="" style="background-color: #f9f9f9; border-width: 2px;">
				</div>
			<br>
			<g:hiddenField name="userListid" value="${usersInstance?.id}" />
			<g:actionSubmit action="updateUser" class="btn btn-success btn-lg px-5 rounded-pill shadow"
				value="Update Details"></g:actionSubmit>
		</g:else>
	
</g:form> 