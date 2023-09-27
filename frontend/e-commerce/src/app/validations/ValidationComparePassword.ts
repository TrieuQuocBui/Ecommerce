import { AbstractControl, ValidatorFn } from "@angular/forms";

export default class ValidationComparePassword{
    static match(controlName:string,checkControlname:string):ValidatorFn{
        return (controls:AbstractControl) =>{
            const control = controls.get(controlName);
            const checkControl = controls.get(checkControlname);
            
            if (checkControl?.errors && !checkControl.errors['matching']){
                return null;
            }

            if (control?.value !== checkControl?.value) {
                checkControl?.setErrors({ matching: true });
                return { matching: true };
              } else {
                return null;
              }
        }
    }
}