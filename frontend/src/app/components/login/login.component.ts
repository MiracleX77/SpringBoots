import { Component } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
    loginFormGroup: FormGroup = new FormGroup({
    email: new FormControl('',Validators.required),
    password: new FormControl('',Validators.required)

  });
  constructor(private userService:UserService){

  }

  onSubmit():void{
    if(this.loginFormGroup.invalid){
      return;
    }
    let email = this.loginFormGroup.controls['email'].value;
    let password = this.loginFormGroup.controls['password'].value;


    this.userService.login(email,password).subscribe((response)=>{
      console.log(response);
    },(error)=>{
      console.log(error);
    });
  }
}
