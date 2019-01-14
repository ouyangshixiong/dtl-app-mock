import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { DtlAppMockSharedModule } from 'app/shared';
import {
    UserLevelComponent,
    UserLevelDetailComponent,
    UserLevelUpdateComponent,
    UserLevelDeletePopupComponent,
    UserLevelDeleteDialogComponent,
    userLevelRoute,
    userLevelPopupRoute
} from './';

const ENTITY_STATES = [...userLevelRoute, ...userLevelPopupRoute];

@NgModule({
    imports: [DtlAppMockSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserLevelComponent,
        UserLevelDetailComponent,
        UserLevelUpdateComponent,
        UserLevelDeleteDialogComponent,
        UserLevelDeletePopupComponent
    ],
    entryComponents: [UserLevelComponent, UserLevelUpdateComponent, UserLevelDeleteDialogComponent, UserLevelDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class DtlAppMockUserLevelModule {}
