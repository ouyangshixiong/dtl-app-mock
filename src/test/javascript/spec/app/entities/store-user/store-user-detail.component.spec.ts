/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { DtlAppMockTestModule } from '../../../test.module';
import { StoreUserDetailComponent } from 'app/entities/store-user/store-user-detail.component';
import { StoreUser } from 'app/shared/model/store-user.model';

describe('Component Tests', () => {
    describe('StoreUser Management Detail Component', () => {
        let comp: StoreUserDetailComponent;
        let fixture: ComponentFixture<StoreUserDetailComponent>;
        const route = ({ data: of({ storeUser: new StoreUser(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [DtlAppMockTestModule],
                declarations: [StoreUserDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(StoreUserDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(StoreUserDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.storeUser).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
