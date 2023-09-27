import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { faHome,faCartShopping,faCircleUser,faCodeCompare,faTruck} from '@fortawesome/free-solid-svg-icons';
import { Role } from 'src/app/models/role.enum';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header-user',
  templateUrl: './header-user.component.html',
  styleUrls: ['./header-user.component.css']
})
export class HeaderUserComponent implements OnInit {
  fashome = faHome;
  fascart = faCartShopping;
  fascompare = faCodeCompare;
  faslogin = faCircleUser;
  isLogged = false;
  fastruck = faTruck;

  constructor(private authService:AuthService,private router:Router) { }

  ngOnInit() {
    this.checkLogged();
  }

  checkLogged(){
    if (this.authService.getUser() && this.authService.getRole() === Role.USER){
      this.isLogged = true;
    }
  }

  redirectToPage(path:string){
    this.router.navigate([path]);
  }

  onLogout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }

}
