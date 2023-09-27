import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { UserModel } from 'src/app/models/user';
import { ProfileService } from 'src/app/services/profile.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
})
export class ProfileComponent implements OnInit {
  profile!: UserModel;
  profileImageUrl!: string; // Update with your image URL
  checkChangeImage: boolean = false;
  selectedImage!:File;

  updateUser: FormGroup = new FormGroup({
    firstName: new FormControl(null),
    lastName: new FormControl(null),
    phoneNumber: new FormControl(null),
    identification: new FormControl(null),
    birthDay: new FormControl(null),
  });

  constructor(
    private formBuilder: FormBuilder,
    private profileService: ProfileService,
  ) {
    this.updateUser = this.formBuilder.group({
      firstName: [''],
      lastName: [''],
      phoneNumber: [''],
      identification: [''],
      birthDay: [''],
    });
  }

  ngOnInit() {
    this.profileService.getProfile().subscribe((result) => {
      this.profile = result;
      // patchValue use update 1 data or much data of FormGroup or FormControl.
      this.updateUser.patchValue(this.profile);
      this.profileImageUrl = `http://localhost:8080/image/${this.profile.image}`;
    });
  }

  loadProfile() {
    this.profileService.getProfile().subscribe((result) => {
      this.profile = result;
    });
  }

  onImageChange(event: any): void {
    // Is the checking changing the image?
    this.checkChangeImage = true;

    // get file image first
    this.selectedImage = event.target.files[0];
    if (this.selectedImage) {

      // create url to display an image when the user alters the image
      this.profileImageUrl = URL.createObjectURL(this.selectedImage);
    }
  }

  saveProfile() {
    this.profileService.update(this.updateUser.value).subscribe((result) => {
      console.log(result);
    });

    if (this.checkChangeImage) {
      // Object formData have used upload file(multipart/form-data)
      const formData = new FormData();
      formData.append('image', this.selectedImage);
      this.profileService.uploadImage(formData).subscribe((result) => {
        console.log(result);
      });

      this.checkChangeImage = false;
    }
  }
}
