

export const URL_BASE = 'http://localhost:8081/api';

export const AUTH_USER = 'authenticatedUser';
export const JWT_TOKEN = 'jwtToken';

export const UPDATE_ERROR = 'Error: Unable to update. Please contact system administrator.';
export const REQUIRED_DESCRIPTION = 'Description is required';
export const REQUIRED_5_DESCRIPTION = 'Description should be atleast 5 characters';
export const REQUIRED_TARGET_DATE = 'Target date is required';

class Utils {

    handleErrorResponse = (error, comp) => {
        const errStr = JSON.stringify(error);
        console.error(`[${comp}][ERROR] ${errStr}`)
        if (error.status) {
            //{"timestamp":"2020-08-24T23:57:30.853+00:00","status":401,"error":"Unauthorized","message":"Unauthorized","path":"/users/test/todos"}
            console.error(`[${comp}][ERROR] ${error.status}: ${error.message}`)
        } else {
            console.error(`[${comp}][ERROR] ${error.name}: ${error.message}`)
            console.error(`[${comp}][ERROR] stack ${error.stack}`)
        }

        //const errorMessage = `${error.name}: ${error.message}`;
        //this.handleState('', errorMessage);
        //console.error(error);
    }
}

export default new Utils();