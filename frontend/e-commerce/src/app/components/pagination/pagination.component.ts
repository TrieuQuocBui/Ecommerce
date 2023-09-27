import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import {faAngleRight,faAngleLeft,faAnglesRight,faAnglesLeft} from '@fortawesome/free-solid-svg-icons'

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {
  @Input() currentPage!: number;
  @Input() totalPages!: number;
  @Input() pageSize!: number;
  @Output() goToPage: EventEmitter<number> = new EventEmitter<number>();

  faAngleRightIcon = faAngleRight;
  faAnglesRightIcon = faAnglesRight;
  faAngleLeftIcon = faAngleLeft;
  faAnglesLeftIcon = faAnglesLeft;

  constructor() { }

  ngOnInit() {
    
  }

  getPagesToShow(): { page: number; isActive: boolean }[] {
    const pagesToShow = [];
    const totalPageToShow = 3; // Số trang hiển thị tối đa (ngoài trừ nút chuyển đến trang đầu và cuối)
    let startPage: number;
  
    if (this.totalPages <= totalPageToShow) {
      // Khi tổng số trang nhỏ hơn hoặc bằng số trang hiển thị tối đa, hiển thị tất cả các trang.
      startPage = 1;
    } else {
      // Khi tổng số trang lớn hơn số trang hiển thị tối đa, tính toán trang bắt đầu để hiển thị.
      startPage = Math.max(1, this.currentPage - Math.floor(totalPageToShow / 2));
      const lastPageToShow = startPage + totalPageToShow - 1;
      if (lastPageToShow > this.totalPages) {
        startPage = this.totalPages - totalPageToShow + 1;
      }
    }
  
    // Tạo danh sách các trang cần hiển thị, đồng thời xác định trang hiện tại để gán CSS class active.
    for (let i = startPage; i < startPage + totalPageToShow && i <= this.totalPages; i++) {
      const pageObject = { page: i, isActive: i === this.currentPage };
      pagesToShow.push(pageObject);
    }

    return pagesToShow;
  }
}
