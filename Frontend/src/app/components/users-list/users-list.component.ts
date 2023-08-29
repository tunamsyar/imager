import { Component, Input } from '@angular/core';
import { CommonModule } from '@angular/common';
import { User } from '../../models/user.model';

@Component({
  standalone: true,
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.scss'],
  imports: [
    CommonModule
  ]
})
export class UsersListComponent {
  userList: User[] =[
    {
      username: 'Some',
      email: 'test@example.com'
    },
    {
      username: 'Some',
      email: 'test@example.com'
    },
    {
      username: 'Some',
      email: 'test@example.com'
    }
  ]
}
