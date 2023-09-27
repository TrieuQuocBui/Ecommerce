
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { MessageModel } from 'src/app/models/message';
import { RoleModel } from 'src/app/models/role';
import { AuthService } from 'src/app/services/auth.service';
import ValidationComparePassword from 'src/app/validations/ValidationComparePassword';
import { faUser,faLock } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  fasuser = faUser;
  faslock = faLock;
  messageError?: string;
  messageSuccess?: string;
  isMessageSuccess:boolean = false;

  role: RoleModel = {
    name: 'USER',
  };

  register: FormGroup = new FormGroup({
    username: new FormControl(null),
    password: new FormControl(null),
    confirmPassword: new FormControl(null),
    role: new FormControl(this.role),
  });

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router:Router
  ) {}

  ngOnInit(): void {
    this.register = this.formBuilder.group(
      {
        username: [null, [Validators.required, Validators.minLength(6)]],
        password: [null, [Validators.required, Validators.minLength(6)]],
        confirmPassword: [null, [Validators.required]],
        role: [this.role],
      },
      {
        validators: [
          ValidationComparePassword.match('password', 'confirmPassword'),
        ],
      }
    );
  }

  get f(): { [key: string]: AbstractControl } {
    return this.register.controls;
  }

  onSubmit() {
    this.authService.createUser(this.register.value).subscribe(
      (results:MessageModel) => {
        this.isMessageSuccess = true;
        this.messageSuccess = results.message;
        setTimeout(() => {
          this.messageError = undefined;
          this.isMessageSuccess = false;
          this.router.navigate(['login']);
        }, 3000);
      },
      (errors) => {
        this.messageError = errors.error.message;
        setTimeout(() => {
          this.messageError = undefined;
        }, 4000);
      }
    );
  }
}
