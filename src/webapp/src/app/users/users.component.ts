import {Component, OnInit} from '@angular/core';
import {AppUser} from './appUser';
import {UsersService} from '../users.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.scss']
})
export class UsersComponent implements OnInit {

  users: Array<AppUser> = [];
  anyData: any[];

  constructor(private usersService: UsersService) { }

  ngOnInit(): void {
    this.usersService.getConfig()
      .toPromise().then((data: AppUser[]) => {
       for (const appUser of data) {
         this.users.push(appUser);
       }
    });
    /*
      .subscribe((data: AppUser) => {
        console.log(data);
        this.users.push(new AppUser(data.username, data.password, data.email, data.role));
        this.anyData.push(data);
      });
     */
  }

  getAllUsers(): Array<AppUser> {
    return this.users;
    // return [new AppUser('wil', 'wil', 'wil@wildev.me', AppUserRole.ADMIN),
    //  new AppUser('bob', 'bob', 'bob@wildev.me', AppUserRole.USER)];
  }

  getAllData(): any[] {
    return this.anyData;
    // return [new AppUser('wil', 'wil', 'wil@wildev.me', AppUserRole.ADMIN),
    //  new AppUser('bob', 'bob', 'bob@wildev.me', AppUserRole.USER)];
  }
}


