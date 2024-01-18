import { CommonModule } from '@angular/common';
import {Component, OnInit} from '@angular/core';
import { HlmButtonModule } from '@spartan-ng/ui-button-helm';

import { BrnMenuTriggerDirective } from '@spartan-ng/ui-menu-brain';
import {
  HlmMenuComponent,
  HlmMenuGroupComponent,
  HlmMenuItemDirective,
  HlmMenuItemIconDirective,
  HlmMenuItemSubIndicatorComponent,
  HlmMenuLabelComponent,
  HlmMenuSeparatorComponent,
  HlmMenuShortcutComponent,
  HlmSubMenuComponent,
} from '@spartan-ng/ui-menu-helm';
import {KeycloakService} from "keycloak-angular";
import {KeycloakProfile} from "keycloak-js";


@Component({
  selector: 'app-narvbar',
  standalone: true,
  imports: [CommonModule,HlmMenuComponent,HlmMenuGroupComponent,HlmMenuItemSubIndicatorComponent,HlmMenuSeparatorComponent,HlmMenuShortcutComponent,HlmSubMenuComponent,HlmMenuLabelComponent,BrnMenuTriggerDirective,HlmMenuItemDirective,HlmMenuItemIconDirective,HlmButtonModule],
  templateUrl: './narvbar.component.html',
  styleUrl: './narvbar.component.css'
})
export class NarvbarComponent implements OnInit{
  public profile? : KeycloakProfile;
  public isAdmin:Boolean;
  public roles:String[]=[]
  constructor(private keycloakService:KeycloakService) {
    this.isAdmin=this.keycloakService.isUserInRole("ADMIN")
  }

  ngOnInit() {
    if(this.keycloakService.isLoggedIn()) {
      this.keycloakService.loadUserProfile().then(profile => {
        this.profile = profile;
      });
    }
    this.roles=this.keycloakService.getUserRoles()
  }

  async login() {
    await this.keycloakService.login({
      redirectUri: window.location.origin
    });
  }


  logout() {
    this.keycloakService.logout(window.location.origin)
  }

}
