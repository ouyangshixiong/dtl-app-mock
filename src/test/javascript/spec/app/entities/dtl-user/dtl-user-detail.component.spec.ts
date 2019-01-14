/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { DTLUserDetailComponent } from 'app/entities/dtl-user/dtl-user-detail.component';
import { DTLUser } from 'app/shared/model/dtl-user.model';

describe('Component Tests', () => {
    describe('DTLUser Management Detail Component', () => {
        let comp: DTLUserDetailComponent;
        let fixture: ComponentFixture<DTLUserDetailComponent>;
        const route = ({ data: of({ dTLUser: new DTLUser(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [DTLUserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(DTLUserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(DTLUserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.dTLUser).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
