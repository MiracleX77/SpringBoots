import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AppCookieService } from 'src/app/services/app-cookie.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerFormGroup: FormGroup = new FormGroup({
    email: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required),
    name:new FormControl('',Validators.required)
  });
  constructor(
    private userService:UserService,
    private appCookieService:AppCookieService,
    private router:Router){
  }

  onSubmit():void{
    if(this.registerFormGroup.invalid){
      return;
    }
    let email = this.registerFormGroup.controls['email'].value;
    let password = this.registerFormGroup.controls['password'].value;
    let name = this.registerFormGroup.controls['name'].value;


    this.userService.register(email,password,name).subscribe((response)=>{
      this.router.navigate(['/login'])
    },(error)=>{
      alert(error.error.error )
    });
  }
}
