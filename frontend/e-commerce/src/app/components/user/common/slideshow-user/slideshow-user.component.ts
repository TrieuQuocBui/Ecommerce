import { Component, OnInit } from '@angular/core';
import { faChevronLeft,faChevronRight} from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-slideshow-user',
  templateUrl: './slideshow-user.component.html',
  styleUrls: ['./slideshow-user.component.css']
})
export class SlideshowUserComponent implements OnInit {
  fasright = faChevronRight;
  fasleft = faChevronLeft;

  constructor(){}
  
  ngOnInit(): void {
   
  }

}
