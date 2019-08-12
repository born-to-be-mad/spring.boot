import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CardListComponent} from "../card-list/card-list.component";
import {UserListComponent} from "../user-list/user-list.component";
import {UserFormComponent} from "../user-form/user-form.component";

@Component({
             selector: 'app-admin-panel',
             templateUrl: './admin-panel.component.html',
             styleUrls: ['./admin-panel.component.css']
           })
export class AdminPanelComponent implements OnInit {
  title: string;
  private buttons: ({ link: string; name: string })[];


  constructor(private route: ActivatedRoute, private router: Router) {
    this.title = 'Admin Panel';
    this.buttons = [
      {link: '/admin/cards', name: 'Cards'},
      {link: '/admin/users', name: 'Users'},
      {link: '/admin/adduser', name: 'Add User'}
    ]
  }

  ngOnInit() {
    this.router.navigate(['/admin/cards']);
  }

}
