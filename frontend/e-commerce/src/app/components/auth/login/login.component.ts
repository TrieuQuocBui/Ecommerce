
import { Component, OnInit } from '@angular/core';
import {
  AbstractControl,
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { NavigationExtras, Router } from '@angular/router';
import { JwtModel } from 'src/app/models/jwt';
import { Role } from 'src/app/models/role.enum';
import { AuthService } from 'src/app/services/auth.service';
import { faUser,faLock } from '@fortawesome/free-solid-svg-icons';
import { CartService } from 'src/app/services/cart.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  fasuser = faUser;
  faslock = faLock;
  messageError?:string;
  messageErrorPassword?:string;
  messageErrorUsername?:string;

  login: FormGroup = new FormGroup({
    usrename: new FormControl(null),
    password: new FormControl(null),
  });

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private router:Router,
    private cartService:CartService
  ) {}

  ngOnInit(): void {
    this.login = this.formBuilder.group({
      username: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

  get f(): { [key: string]: AbstractControl } {
    return this.login.controls;
  }

  onKeydownUsername(event:any){
    if (this.messageErrorUsername){
      this.messageErrorUsername = undefined;
    }
  }

  onKeydownPassword(event:any){
    if (this.messageErrorPassword){
      this.messageErrorPassword = undefined;
    }
  }

  onSubmit() {
    this.authService.login(this.login.value).subscribe(
      (result:JwtModel) => {
        this.authService.saveToken(result.token!);
        this.authService.saveUser(result.account);

        if ( this.authService.getRole() === Role.USER){
          this.cartService.LoggedCart();
          const previousUrl = localStorage.getItem('previousUrl');
          if (previousUrl) {
            const navigationExtras: NavigationExtras = {
              queryParamsHandling: 'preserve', // Giữ nguyên các query params
              preserveFragment: true // Giữ nguyên fragment
            };
            this.router.navigateByUrl(previousUrl, navigationExtras);
          } else {
            // Nếu không có URL trước đó, chuyển hướng đến trang mặc định
            this.router.navigate(['/home']);
          }
        } else if(this.authService.getRole() === Role.ADMIN){
          this.router.navigate(['/admin/home']);
        }
      },
      (error) => {
  
        if (error.error.message.includes("Tài khoản")){
          this.messageErrorUsername = error.error.message;
        } else if (error.error.message.includes("Mật khẩu")){
          this.messageErrorPassword = error.error.message;
        } else {
          this.messageError = error.error.message;
        }
      }
    );
  }
}
