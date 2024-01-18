import { Component } from '@angular/core';
import { BrnMenuGroupDirective } from '@spartan-ng/ui-menu-brain';

@Component({
	selector: 'hlm-menu-group',
	standalone: true,
	host: {
		class: 'block bg-white',
	},
	hostDirectives: [BrnMenuGroupDirective],
	template: `
		<ng-content />
	`,
})
export class HlmMenuGroupComponent {}
