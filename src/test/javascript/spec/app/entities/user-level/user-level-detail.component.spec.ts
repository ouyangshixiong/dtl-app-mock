/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { UserLevelDetailComponent } from 'app/entities/user-level/user-level-detail.component';
import { UserLevel } from 'app/shared/model/user-level.model';

describe('Component Tests', () => {
    describe('UserLevel Management Detail Component', () => {
        let comp: UserLevelDetailComponent;
        let fixture: ComponentFixture<UserLevelDetailComponent>;
        const route = ({ data: of({ userLevel: new UserLevel(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [UserLevelDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(UserLevelDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(UserLevelDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.userLevel).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
