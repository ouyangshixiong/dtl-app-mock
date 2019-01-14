import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    UserRoleComponent,
    UserRoleDetailComponent,
    UserRoleUpdateComponent,
    UserRoleDeletePopupComponent,
    UserRoleDeleteDialogComponent,
    userRoleRoute,
    userRolePopupRoute
} from './';

const ENTITY_STATES = [...userRoleRoute, ...userRolePopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserRoleComponent,
        UserRoleDetailComponent,
        UserRoleUpdateComponent,
        UserRoleDeleteDialogComponent,
        UserRoleDeletePopupComponent
    ],
    entryComponents: [UserRoleComponent, UserRoleUpdateComponent, UserRoleDeleteDialogComponent, UserRoleDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockUserRoleModule {}
